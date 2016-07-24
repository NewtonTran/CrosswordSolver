# CrosswordSolver

This program was originally an assignment written in Racket.
I decided to redo the assignment in Java so I had to restructure the program to make it object oriented.

To use this program,
1. create a .txt file of the following format,

      ;; comments must start with a semi colon.
      ;; this first number is the width of the board.
      ;; all boards must be a square.
      5
      ;; this is the board. A '#' is an empty cell, a '.' is an void cell.
      #.#..
      #.#..
      #####
      #.#.#
      ..#.#
      ;; this next number is the number of words on the board.
      4
      ;; these are the words that go on the board.
      SALT
      LEASE
      TRACK
      EEL

2. save the file at any location.
3. open up the Main class provided and put the file path as a parameter for the Solver constructor.
4. run the program.

If the puzzle was completed, the program will output a completed board to the console.
Otherwise, the program will print "The puzzle could not be completed.".
