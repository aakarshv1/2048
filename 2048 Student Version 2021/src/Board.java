import java.util.Random;

public class Board {
 
	private int[][] board; // holds state of game
	private Random rnd = new Random(0); // setup random # generator
	
	//What instance variable can you add to keep track of the size or the number of tiles occupied?
	private int size;
	/* default constructor for board */
	// constructors must match exactly the name
	// of the class.
	public Board() {
		
		// instantiate the board
		board = new int[4][4];
		populateOne();
		populateOne();
		this.size = 2;
	}

	/*
	 * return a String representation of the 2D array board
	 * each row should be in its own line
	 * 
	 * Example:
	 * 
	 * { {1, 2, 3}, {4, 5, 6}} -> 1 2 3
	 * 
	 * 4 5 6
	 */

	
	// overriding a method is when a "child"
	// class implement the exact same method
	// that its parent class has
	public String toString() {
		
		/*
		 * Use the String formatter to pad the numbers with leading 0s
		 * so that the print out does not become jagged
		 * An example is shown below. 
		 * String str = String.format("%04d", 9);  // 0009  
		 * int x = 30;
		 * System.out.println(String.format("%04d",x));
		 *     
		 */
		
		//setup loops to visit
		//every spot possible
		String str = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {;
				str += String.format("%04d", board[i][j]) + " ";
			}
			str += "\n";
		};
		return str;
	}

	/*
	 * set one of the empty spaces (at random)
	 * to a 2 or 4 (90/10 chance). an empty spot is defined to be a 0 element
	 * Must use the Random class object rnd.
	 * Example Use of rnd object.
	 * 
	 * int randomNum = rnd.nextInt(10); //returns a number in range [0 10) (not
	 * inclusive on the 10)
	 */

	public void populateOne() {
		
		// is there an empty spot?
		// for randomness, generate a row and column
		// check if that tile is empty, if it is NOT empty,
		// generate another set of row and column
		// what happens if the entire board is full??! 
		boolean unfound = true;
		while (unfound) {
			int i = rnd.nextInt(4);
			int j = rnd.nextInt(4);
			if (board[i][j] == 0) {
				unfound = false;
				double p = Math.random();
				if (p > 0.9)
					board[i][j] = 4;
				else
					board[i][j] = 2;
			}
			
		}
		
		
		
			

	}

	/*
	 * 
	 * Given an array of integers, slide all non-zero elements to the right.
	 * zero elements are considered open spots.
	 * example:
	 * 
	 * [0 2 0 2]->[0 0 2 2]
	 * [2 8 0 2]->[0 2 8 2]
	 * [4 0 0 0]->[0 0 0 4]
	 */

	public void slideRight(int[] row) {
		for (int i = 3; i > 0; i--) {
			if (row[i] == 0) {
				int c = 0;
				for(int j = i-1; j >= 0; j--) {
					if (row[j] != 0 && c == 0) {
						row[i] = row[j];
						row[j] = 0;
						c++;
					}
				}
			}
		}

	
	}

	/*
	 * 
	 * Move the numbers as far to the right as they can go
	 * aka the numbers are trying to move to the right-most
	 * empty spaces. This method must utilize the slideRight(int[] row) method
	 * must utilize the helper method above for full credit.
	 * param: a valid row of 2048 where 0s are "empty" spots
	 * effect: row is modified so all numbers are to the right side
	 * return: none
	 */

	public void slideRight() {

		// go through 2D array, move all digits as far right as possible
		//setup a loop to grab ONE row at a time from 2d array board
		for (int[] row : board) {
			slideRight(row);
		}
	
		
	}

	/**
	 * Given an array of integers, slide all non-zero elements to the left.
	 * zero elements are considered open spots.
	 * example:
	 * 
	 * [0 2 0 2] -> [2 2 0 0]
	 * [2 0 0 2] -> [2 2 0 0]
	 */

	public void slideLeft(int[] arr) {
		
		for (int i = 0; i < 3 ; i++) {
			if (arr[i] == 0) {
				int c = 0;
				for(int j = i+ 1; j < 4; j++) {
					if (arr[j] != 0 && c == 0) {
						arr[i] = arr[j];
						arr[j] = 0;
						c++;
					}
				}
			}
		}
		
	}

	/*
	 * Slide all the numbers to the left so that
	 * 
	 * all of the empty spaces are on the right side
	 */

	public void slideLeft() {
		
		// grabbing a row from a 2D array
		// if it's called arr then arr[i] grabs ONE row!
	
		for (int[] arr : board) {
			slideLeft(arr);
		}
		
		//visit every single row in the 2D array
		//call the slideLeft method that takes in one argument
		
		
	}

	/**
	 * Given a 2D array and a column number, return a 1D array representing the
	 * elements in the given column number.
	 */
	public int[] getCol(int[][] data, int c) {
		
		int[] col = new int[4];
		for (int i = 0; i < 4; i++) {
			col[i] = data[i][c];
		}
		
		//you can also add print out statements here
		return col;
		
	}

	/**
	 * Given an array of integers, slide all non-zero elements to the top.
	 * 
	 * zero elements are considered open spots.
	 */

	public void slideUp(int[] arr) {
		/* calls a helper method */
		// do not rewrite logic you already have!
		slideLeft(arr);
	}

	/*
	 * 
	 * Slide all elements in the board towards the top.
	 * 
	 * You must use slideUp and getCol for full credit.
	 */
	public void slideUp() {
		
		//visit every column index
		//grab each column as an array using getCol -> keep track of it in a 1d array
		// variable/reference
		//have slideLeft perform manipulation on the array
		// copy over the 1D array representation of the column
		// back to the 2D board array
		
		for (int i = 0; i < 4; i++) {
			int[] c = getCol(board, i);
			slideUp(c);
			for (int j = 0; j < 4; j++) {
				board[j][i] = c[j];
			}
		}

		
		
		
	}

	public void slideDown(int[] arr) {

		slideRight(arr);
	}

	/*
	 * slide all the numbers down so that any
	 * empty space is at the top
	 * You must use slideDown and getCol for full credit.
	 */

	public void slideDown() {
		
		for (int i = 0; i < 4; i++) {
			int[] c = getCol(board, i);
			slideDown(c);
			for (int j = 0; j < 4; j++) {
				board[j][i] = c[j];
			}
		}

	}

	/*
	 * Given the 2D array, board, combineRight will take adjacent numbers that
	 * are the same and combine them (add them).
	 * After adding them together, one of the numbers is zeroed out. For
	 * example, if row 0 contained [0 0 4 4],
	 * a call to combineRight will produce [0 0 0 8]. If row 1 contained [2 2 2
	 * 2], a call to combineRight will
	 * produce [0 4 0 4].
	 * 
	 * Notice that the left element is zeroed out.
	 */

	public void combineRight() {
		for (int j = 0; j < 4; j++) {
			for (int i = 3; i > 0; i--) {
				if (board[j][i] == board[j][i-1]) {
					board[j][i] *= 2;
					board[j][i-1] = 0;
				}
			}
			
		}

	}

	/*
	 * same behavior as combineRight but the right element is zeroed out when
	 * two elements are combined
	 */

	public void combineLeft() {
		
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++) {
				if (board[j][i] == board[j][i+1]) {
					board[j][i] *= 2;
					board[j][i+1] = 0;
				}
			}
			
		}
	}
	
	/*
	 * same behavior as combineRight but the bottom element is zeroed out when
	 * two elements are combined
	 */

	public void combineUp() {
		
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 4; i++) {
				if (board[j][i] == board[j+1][i]) {
					board[j][i] *= 2;
					board[j+1][i] = 0;
				}
			}
			
		}

	}

	/*
	 * same behavior as combineRight but the top element is zeroed out when two
	 * elements are combined
	 */

	public void combineDown() {
		
		for (int j = 0; j < 4; j++) {
			for (int i = 3; i > 0; i--) {
				if (board[i][j] == board[i-1][j]) {
					board[i][j] *= 2;
					board[i-1][j] = 0;
				}
			}
			
		}

	}

	
	
	/* reminder: these are the methods that will ultimately invoke
	 * a series of methods
	 * 
	 * the combine and slide methods should not worry about each other's methods
	 */
	public void left() {
		//1) numbers slide to the left
		//2) combine
		//3) slide
		slideLeft();
		combineLeft();
		slideLeft();
	}

	public void right() {
		
		slideRight();
		combineRight();
		slideRight();

	}

	public void up() {
		
		slideUp();
		combineUp();
		slideUp();

	}

	public void down() {
		
		slideDown();
		combineDown();
		slideDown();

	}
	
	

	public boolean gameOver() {
		
		return false;
	}

	public int[][] getBoard() {
		return board;
	}

	// populate with a given 2d array
	public void populate(int[][] arr) {
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				board[r][c] = arr[r][c];
			}
		}
	}

}
