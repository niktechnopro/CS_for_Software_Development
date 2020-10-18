import java.util.*;

public class WhackAMole {

	int score;
	int molesLeft;
	int attemptsLeft;
	char[][] moleGrid;
	
	/**The constructor for the game. It specifies the number
     * of whacks allowed, and the grid dimension.*/
	WhackAMole(int numAttempts, int gridDimension) {
		attemptsLeft = numAttempts;
		molesLeft = 0;
		moleGrid = new char[gridDimension][gridDimension];
		for ( int row = 0; row < moleGrid.length; row ++ ) {
			for( int col = 0; col < moleGrid[row].length; col ++ ) {
				moleGrid[row][col] = '*';
			}
		}

	}
	
	//Given a location, place a mole at that location.
    //Return true if I can. Also update the number of moles left.
	public boolean place(int x, int y) {
		
		if( moleGrid[x][y] != 'M') {
			moleGrid[x][y] = 'M';
			molesLeft ++;
			return true;
		}
		return false;
	}
	
	/** Given a location, take a whack at that location.
     * If that location contains a mole, the score, number
     * of attemptsLeft, and molesLeft is updated. If that
     * location does not contain a mole, only attemptsLeft
     * is updated.*/
	public void whack(int x, int y) {
		if ( moleGrid[x][y] == 'M' ) {
			moleGrid[x][y] = 'W';
			System.out.println("A whack at " + x + "," + y + " !");
			score += 1;
			attemptsLeft -= 1;
			molesLeft -= 1;
			System.out.println("Score: " + score);
            System.out.println("Moles left: " + molesLeft);
		}
		else {
			System.out.println( "You missed!" );
			attemptsLeft -= 1;
		}
		System.out.println("Attempts left: " + attemptsLeft);
	}
	
	/**Prints the grid without showing where the moles are.
     * For every spot that has recorded a "whacked mole",
     * print out a W, or * otherwise.*/
	public void printGridToUser() {
		for ( int row = 0; row < moleGrid.length; row ++ ) {
			for( int col = 0; col < moleGrid[row].length; col ++ ) {
				if ( moleGrid[row][col] == 'M') {
					System.out.print('*');
				}
				else System.out.print(moleGrid[row][col]);
			}
			System.out.println();
		}
	}
	
	/**Print the grid completely. This is effectively
     * dumping the 2d array on the screen. The places where
     * the moles are, are printed as 'M'. The places where
     * the moles have been whacked are printed as 'W'. The
     * places that don't have a mole are printed as '*'.*/
	public void printGrid() {
		for ( int row = 0; row < moleGrid.length; row ++ ) {
			for( int col = 0; col < moleGrid[row].length; col ++ ) {
				System.out.print(moleGrid[row][col]);
			}
			System.out.println();
		}
	}
	
  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Initializing the game by a 10 x 10 grid and give user 2 attempts
		WhackAMole mole = new WhackAMole(2, 10);
		
		// Randomly place 10 moles.
		Random random = new Random();

		while( mole.molesLeft != 10 ) {
			int moleX = random.nextInt( 10 );
			int moleY = random.nextInt( 10 );
			mole.place( moleX, moleY );
		}
		
		// Print a first grid for the user
		mole.printGridToUser();
		
		// Start whacking.
		while ( mole.attemptsLeft > 0 && mole.molesLeft > 0 ) {
			
			// Ask for inputting the coordinates
			Scanner input = new Scanner(System.in);
			System.out.print("You have a maximum of " + mole.attemptsLeft + " attempts to whack the mole. "
					+ "If you want to give up put in -1 twice.\n Enter x and y: ");
			int x = input.nextInt();
			int y = input.nextInt();
			
			// Exit the loop if give up
			if( x == -1 && y == -1 ) {
				System.out.println("User Exit !");
				mole.printGrid();
				break;
			}
			
			else {
				mole.whack( x, y );
				mole.printGridToUser();
			}
		}
		if (mole.attemptsLeft == 0) {
            System.out.println("Game over. No attempts left.");
            mole.printGrid();
        } else if (mole.molesLeft == 0) {
            System.out.println("Congratulations. You Won!");
        }
	}

}
