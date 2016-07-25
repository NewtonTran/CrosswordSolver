package crosswordSolverPackage;

import java.util.ArrayList;

/**
 * @classname State
 * @author Newton Tran
 * @description This class contains methods that will change the state of the crossword puzzle.
 */

public class State {

	// Attributes
	private CrosswordBoard board;
	private int wordsLeft; // number of words left
	private ArrayList<WordPosition> positionList; // list of unused positions
	private ArrayList<String> wordList; // list of unused words

	/**
	 * @methodname State
	 * @description Constructor, takes a new CrosswordBoard and a new ArrayList<String> containing all
	 * words that could go into the puzzle. This constructor is mainly used for the initial state of the
	 * puzzle.
	 */
	public State(CrosswordBoard newBoard, ArrayList<String> newWordList) {
		this.board = newBoard;
		this.wordsLeft = newWordList.size();
		this.wordList = newWordList;
		this.positionList = new ArrayList<>();
		findWordPositions();
	}
	
	/**
	 * @methodname State
	 * @description Constructor, same as previous method, except in addition to the existing parameters, it
	 * takes a new ArrayList<WordPosition>. This constructor is mainly used for new neighbouring states.
	 */
	public State(CrosswordBoard newBoard, ArrayList<String> newWordList, ArrayList<WordPosition> positionList) {
		this.board = newBoard;
		this.wordsLeft = newWordList.size();
		this.wordList = newWordList;
		this.positionList = positionList;
	}

	/**
	 * @methodname findWordPositions
	 * @description a wrapper function that generates an ArrayList<WordPosition> containing all possible WordPositions
	 * for the current State.
	 */
	private void findWordPositions() {
		for (int i = 0; i < this.board.getWidth(); ++i) {
			findWordPositionsRow(this.board.getBoard()[i], i, 0, 0, true);
		}
		for (int i = 0; i < this.board.getWidth(); ++i) {
			findWordPositionsCol(this.board.getTranspose()[i], 0, i, 0, false);
		}

	}

	/**
	 * @methodname findWordPositionsRow
	 * @description given the char[] representing a row of the puzzle, this method generates an ArrayList<WordPosition>
	 * containing all WordPositions in the column, stores them in positionList, then returns the number of WordPositions
	 * found.
	 */
	private int findWordPositionsRow(char[] loc, int row, int col,
			int wordCount, boolean horizontal) {
		if (this.board.getWidth() == col + 1 || this.board.getWidth() == col) {
			return wordCount;
		} else if (loc[col] == '.' || loc[col + 1] == '.') {
			return findWordPositionsRow(loc, row, col + 1, wordCount,
					horizontal);
		} else {
			int wordLength = countLength(loc, col, 0);
			WordPosition newWordPosition = new WordPosition(row, col,
					horizontal, wordLength);
			this.positionList.add(newWordPosition);
			return findWordPositionsRow(loc, row, col + wordLength,
					wordCount + 1, horizontal);
		}
	}

	/**
	 * @methodname findWordPositionsCol
	 * @description given the char[] representing a column of the puzzle, this method generates an ArrayList<WordPosition>
	 * containing all WordPositions in the column, stores them in positionList, then returns the number of WordPositions
	 * found.
	 */
	private int findWordPositionsCol(char[] loc, int row, int col,
			int wordCount, boolean horizontal) {
		if (this.board.getWidth() == row + 1 || this.board.getWidth() == row) {
			return wordCount;
		} else if (loc[row] == '.' || loc[row + 1] == '.') {
			return findWordPositionsCol(loc, row + 1, col, wordCount,
					horizontal);
		} else {
			int wordLength = countLength(loc, row, 0);
			WordPosition newWordPosition = new WordPosition(row, col,
					horizontal, wordLength);
			this.positionList.add(newWordPosition);
			return findWordPositionsCol(loc, row + wordLength, col,
					wordCount + 1, horizontal);
		}
	}

	/**
	 * @methodname countLength
	 * @description this method returns the number of consecutive '#'s in the char[] provided.
	 */
	private int countLength(char[] loc, int col, int acc) {
		if (this.board.getWidth() == col || loc[col] == '.') {
			return acc;
		} else {
			return countLength(loc, col + 1, acc + 1);
		}
	}

	/**
	 * @methodname extractWordPosition
	 * @description this method returns the char[] representing the WordPosition provided.
	 */
	public char[] extractWordPosition(WordPosition wordPosition) {
		int wordLength = wordPosition.getLength();
		int row = wordPosition.getRow();
		int col = wordPosition.getCol();
		if (wordPosition.isHorizontal()) {
			String extractedRow = new String(this.board.getBoard()[row]);
			return extractedRow.substring(col, col + wordLength).toCharArray();
		} else {
			String extractedRow = new String(this.board.getTranspose()[col]);
			return extractedRow.substring(row, row + wordLength).toCharArray();
		}
	}

	/**
	 * @methodname fit
	 * @description this method returns true if a word would fit on the board.
	 */
	public static boolean fit(char[] locBoard, char[] loc) {
		if (locBoard.length != loc.length) {
			return false;
		}
		for (int i = 0; i < locBoard.length; ++i) {
			if (locBoard[i] != loc[i] && locBoard[i] != '#') {
				return false;
			}
		}
		return true;
	}

	/**
	 * @methodname replaceWordPosition
	 * @description this method places word in the puzzle at the position represented by wordPosition
	 */
	public void replaceWordPosition(WordPosition wordPosition, String word) {
		int row = wordPosition.getRow();
		int col = wordPosition.getCol();
		int length = wordPosition.getLength();
		char[] wordArr = word.toCharArray();

		if (wordPosition.isHorizontal()) {
			for (int i = 0; i < length; ++i) {
				this.board.getBoard()[row][i + col] = wordArr[i];
			}
		} else {
			for (int i = 0; i < length; ++i) {
				this.board.getBoard()[i + row][col] = wordArr[i];
			}
		}
		this.board.updateTranspose();
		for (int i = 0; i < this.wordsLeft; ++i) {
			if (this.wordList.get(i).equals(word)) {
				this.wordList.remove(i);
				break;
			}
		}
		
		for (int i = 0; i < this.wordsLeft; ++i) {
			if (this.positionList.get(i).equals(wordPosition)) {
				this.positionList.remove(i);
				break;
			}
		}

		--this.wordsLeft;
	}
	
	/**
	 * @methodname countFilledSpaces
	 * @description this method counts the number of '#'s in loc and returns the total count.
	 */
	public int countFilledSpaces(char[] loc) {
		int filledSpaces = 0;
		for (int i = 0; i < loc.length; ++i) {
			if (loc[i] != '#') {
				++filledSpaces;
			}
		}
		return filledSpaces;
	}
	
	/**
	 * @methodname chooseWordPosition
	 * @description this method chooses the WordPosition from positionList that has the most filled spaces and
	 * returns it.
	 */
	public WordPosition chooseWordPosition() {
		WordPosition chosen = null;
		int maxFilledSpaces = 0;
		for (int i = 0; i < this.wordsLeft; ++i) {
			if (chosen == null ||
					countFilledSpaces(extractWordPosition(this.positionList.get(i))) > maxFilledSpaces) {
				chosen = this.positionList.get(i);
				maxFilledSpaces = countFilledSpaces(extractWordPosition(this.positionList.get(i)));
			}
		}
		return chosen;
	}

	/**
	 * @methodname neighbours
	 * @description this method returns an ArrayList<State> containing all possible moves that could be done
	 * at the current state of the puzzle. In other words, this method generates a list of States that have
	 * one more word placed in the puzzle than the current state.
	 */
	public ArrayList<State> neighbours() {
		WordPosition chosen = new WordPosition(chooseWordPosition());
		char[] extractedWordPosition = extractWordPosition(chosen);
		ArrayList<State> neighbours = new ArrayList<>();
		for (int i = 0; i < this.wordsLeft; ++i) {
			if (fit(extractedWordPosition, this.wordList.get(i).toCharArray())) {
				CrosswordBoard newBoard = new CrosswordBoard(this.board);
				ArrayList<String> newWordList = new ArrayList<>();
				ArrayList<WordPosition> newPositionList = new ArrayList<>();
				for (int j = 0; j < this.wordsLeft; ++j) {
					newWordList.add(this.wordList.get(j));
				}
				for (int k = 0; k < this.wordsLeft; ++k) {
					newPositionList.add(this.positionList.get(k));
				}
				State newState = new State(newBoard, newWordList, newPositionList);
				newState.replaceWordPosition(chosen, newState.wordList.get(i));
				neighbours.add(newState);
			}
		}
		return neighbours;
	}

	/**
	 * @methodname getPositionList
	 * @description returns the current positionList
	 */
	public ArrayList<WordPosition> getPositionList() {
		return this.positionList;
	}
	
	/**
	 * @methodname getWordsLeft
	 * @description returns the number of words left in the puzzle.
	 */
	public int getWordsLeft() {
		return this.wordsLeft;
	}
	
	/**
	 * @methodname getCrosswordBoard
	 * @description returns the current CrosswordBoard.
	 */
	public CrosswordBoard getCrosswordBoard() {
		return this.board;
	}
	
	/**
	 * @methodname printWordList
	 * @description prints all words in wordList to the screen.
	 */
	public void printWordList() {
		if (this.wordsLeft == 0) {
			System.out.println("The puzzle has no words");
		} else if (this.wordsLeft == 1) {
			System.out.println("The word in the puzzle is: ");
		} else {
			System.out.println("The words in the puzzle are: ");
		}
		for (int i = 0; i < this.wordsLeft; ++i) {
			System.out.println(this.wordList.get(i));
		}
		System.out.println("\n");
	}
	
	/**
	 * @methodname printPositionList
	 * @description prints all WordPositions in positionList to the screen.
	 */
	public void printPositionList() {
		for (int i = 0; i < this.board.getWidth(); ++i) {
			this.positionList.get(i).printWordPosition();
		}
	}
}
