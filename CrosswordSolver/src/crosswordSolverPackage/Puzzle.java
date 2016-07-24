package crosswordSolverPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @classname Puzzle
 * @description this class reads a puzzle from a plain txt file.
 */

public class Puzzle {

	// Constants
	static char COMMENT_CHAR = ';';
	static char UNUSED_CELL = '.';
	static char EMPTY_CELL = '#';
	
	// Attributes
	private int width; // all puzzles must be in square form, thus only 1 measurement is required.
	private int words; // total number of words in the puzzle
	private ArrayList<String> stringBoard; // board formatted as ArrayList<String> instead of char[][]
	private ArrayList<String> wordList; // all words in the puzzle

	/**
	 * @methodname Puzzle
	 * @description Constructor, takes a file name and reads that file to produce a Puzzle
	 */
	public Puzzle(String fileName) {
		String line = null;
		int counter = 0; // determines which variable the number should be stored with (width or words)
		wordList = new ArrayList<>();
		stringBoard = new ArrayList<>();
		try {
			
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			line = bufferedReader.readLine();
			
			while (line != null) {
				if (line.startsWith(String.valueOf(COMMENT_CHAR))) {
					line = bufferedReader.readLine();
					continue;
				}
				else if (isNumeric(line)) {
					if (counter == 0) {
						this.width = Integer.parseInt(line);
						++counter;
					}
					else {
						this.words = Integer.parseInt(line);
					}
				}
				else if (line.startsWith(String.valueOf(UNUSED_CELL)) || 
						 line.startsWith(String.valueOf(EMPTY_CELL))) {
					this.stringBoard.add(line);
				}
				else {
					this.wordList.add(line);
				}
				line = bufferedReader.readLine();
			}
			
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
		
	}
	
	/**
	 * @methodname isNumeric
	 * @description returns true if the String given is numeric, otherwise false is returned.
	 */
	static boolean isNumeric(String str) {  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	/**
	 * @methodname getWidth
	 * @description returns the width of the board.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * @methodname getWords
	 * @description returns the total number of words in the Puzzle.
	 */
	public int getWords() {
		return this.words;
	}
	
	/**
	 * @methodname getStringBoard
	 * @description returns the stringBoard.
	 */
	public ArrayList<String> getStringBoard() {
		return this.stringBoard;
	}
	
	/**
	 * @methodname getWordList
	 * @description returns the wordList.
	 */
	public ArrayList<String> getWordList() {
		return this.wordList;
	}
}
