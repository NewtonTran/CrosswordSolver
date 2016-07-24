package crosswordSolverPackage;

import java.util.ArrayList;

/**
 * @classname Solver
 * @description this class solves a given crossword puzzle.
 */

public class Solver {

	// Attributes
	private boolean complete; // true if the puzzle is complete
	private State completedState; // completed puzzle is stored here
	
	/**
	 * @methodname Solver
	 * @description Constructor, takes a file name and solves the puzzle in that file.
	 */
	public Solver(String fileName) {
		Puzzle puzzle = new Puzzle(fileName);
		CrosswordBoard initialBoard = new CrosswordBoard(puzzle);
		State initialState = new State(initialBoard, puzzle.getWordList());
		this.complete = false;
		this.completedState = null;
		solve(initialState);
	}
	
	/**
	 * @methodname solve
	 * @description this method is mutually recursive with solveList. This method solves a puzzle from
	 * a given State.
	 */
	public boolean solve(State state) {
		if (state.getWordsLeft() == 0) {
			return true;
		}
		ArrayList<State> neighbours = state.neighbours();
		if (neighbours.isEmpty()) {
			return false;
		}
		else if (solveList(neighbours)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @methodname solveList
	 * @description this method is mutually recursive with solve. This method helps solve a puzzle from
	 * a given State.
	 */
	public boolean solveList(ArrayList<State> neighbours) {
		for (int i = 0; i < neighbours.size(); ++i) {
			if (solve(neighbours.get(i))) {
				if (!this.complete) {
					completedState = neighbours.get(i);
					complete = true;
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @methodname isComplete
	 * @description returns true if the puzzle is complete, otherwise false is returned.
	 */
	public boolean isComplete() {
		return this.complete;
	}
	
	/**
	 * @methodname getCompletedState
	 * @description returns the completed state if the Puzzle is complete, otherwise null is returned.
	 */
	public State getCompletedState() {
		if (this.complete) {
			return this.completedState;
		}
		else {
			return null;
		}
	}
}
