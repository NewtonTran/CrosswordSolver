package crosswordSolverPackage;

/**
 * @classname WordPosition
 * @description an object of this class will define a word position on a CrosswordBoard.
 */

public class WordPosition {

	// Attributes
	private int row; // row and col define the starting point of the word on the board.
	private int col;
	private boolean horizontal; // determines if the word is horizontal or vertical.
	private int length; // length of the word that can fit in this position.
	
	/**
	 * @methodname WordPosition
	 * @description Constructor, creates a WordPosition with the given parameters.
	 */
	public WordPosition(int row, int col, boolean horizontal, int length) {
		this.row = row;
		this.col = col;
		this.horizontal = horizontal;
		this.length = length;
	}
	
	/**
	 * @methodname WordPosition
	 * @description Constructor, creates a new WordPosition identical to the one given.
	 */
	public WordPosition(WordPosition wordPosition) {
		this.row = wordPosition.getRow();
		this.col = wordPosition.getCol();
		this.horizontal = wordPosition.isHorizontal();
		this.length = wordPosition.getLength();
	}
	
	/**
	 * @methodname getRow
	 * @description returns the row number.
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * @methodname getCol
	 * @description returns the col number.
	 */
	public int getCol() {
		return this.col;
	}
	
	/**
	 * @methodname isHorizontal
	 * @description returns true if the word is horizontal, false if the word is vertical.
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	/**
	 * @methodname getRow
	 * @description returns the length of the word.
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * @methodname printWordPosition
	 * @description prints the data within this WordPosition in the following format: row, col, horizontal, length
	 */
	public void printWordPosition() {
		System.out.print(this.row + ", " + this.col + ", " + this.horizontal + ", " + this.length + "\n");
	}
	
	/**
	 * @methodname equals
	 * @description returns true if the given WordPosition is logically equivalent to this WordPosition.
	 */
	public boolean equals(WordPosition wordPosition) {
		if (this.row == wordPosition.getRow() && this.col == wordPosition.getCol() &&
				this.horizontal == wordPosition.isHorizontal() && this.length == wordPosition.getLength()) {
			return true;
		}
		else {
			return false;
		}
	}
}