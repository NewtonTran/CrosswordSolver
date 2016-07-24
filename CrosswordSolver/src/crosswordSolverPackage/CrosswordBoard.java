package crosswordSolverPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @classname CrosswordBoard
 * @description an object of this class represents a crossword board.
 */

public class CrosswordBoard {

	// Attributes
	private int width; // all puzzles must be in square form, thus only 1 measurement is required.
	private char[][] board; // char[][] representing the board.
	private char[][] transpose; // char[][] representing the transpose of board.

	/**
	 * @methodname CrosswordBoard
	 * @description Constructor, creates a CrosswordBoard with data from a given puzzle.
	 */
	public CrosswordBoard(Puzzle puzzle) {
		this.width = puzzle.getWidth();
		
		this.board = new char[this.width][this.width];
		for (int i = 0; i < this.width; ++i) {
			this.board[i] = puzzle.getStringBoard().get(i).toCharArray();
		}

		this.transpose = new char[this.width][this.width];
		updateTranspose();

	}
	
	/**
	 * @methodname CrosswordBoard
	 * @description Constructor, creates a CrosswordBoard identical to the given CrosswordBoard.
	 */
	public CrosswordBoard(CrosswordBoard board) {
		this.width = board.width;
		this.board = new char[this.width][this.width];
		for (int i = 0; i < this.width; ++i) {
			for (int j = 0; j < this.width; ++j) {
				this.board[i][j] = board.board[i][j];
			}
		}
		
		this.transpose = new char[this.width][this.width];
		updateTranspose();
	}

	/**
	 * @methodname updateTranspose
	 * @description updates transpose if any changes are made to board.
	 */
	public void updateTranspose() {
		for (int i = 0; i < this.width; ++i) {
			for (int j = 0; j < this.width; ++j) {
				this.transpose[j][i] = board[i][j];
			}
		}
	}
	
	/**
	 * @methodname updateBoard
	 * @description updates the board if any changes are made to transpose.
	 */
	public void updateBoard() {
		for (int i = 0; i < this.width; ++i) {
			for (int j = 0; j < this.width; ++j) {
				this.board[j][i] = transpose[i][j];
			}
		}
	}
	
	/**
	 * @methodname getWidth
	 * @description returns the width of this CrosswordBoard.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @methodname getBoard
	 * @description returns the board of this CrosswordBoard.
	 */
	public char[][] getBoard() {
		return this.board;
	}
	
	/**
	 * @methodname getWidth
	 * @description returns the transpose of this CrosswordBoard.
	 */
	public char[][] getTranspose() {
		return this.transpose;
	}

	/**
	 * @methodname printBoard
	 * @description prints board to the screen.
	 */
	public void printBoard() {
		for (int i = 0; i < this.width; ++i) {
			for (int j = 0; j < this.width; ++j) {
				System.out.print(this.board[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	/**
	 * @methodname printTranspose
	 * @description prints transpose to the screen.
	 */
	public void printTranspose() {
		for (int i = 0; i < this.width; ++i) {
			for (int j = 0; j < this.width; ++j) {
				System.out.print(this.transpose[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}