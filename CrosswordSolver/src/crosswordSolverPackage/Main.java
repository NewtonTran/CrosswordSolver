package crosswordSolverPackage;

import java.util.ArrayList;

/**
 * @classname Main
 * @description to use this program, create a .txt file in the correct format, place the file path below, then run.
 */

public class Main {
	
	public static void main(String[] args) {
		Solver solver = new Solver("Place file path here.");
		if (solver.isComplete()) {
			solver.getCompletedState().getCrosswordBoard().printBoard();
		}
		else {
			System.out.println("The puzzle could not be completed.");
		}
	}
	
}