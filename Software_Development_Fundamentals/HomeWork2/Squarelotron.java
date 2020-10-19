import java.util.Arrays;

public class Squarelotron {
	// Class instance variables
	int[][] squarelotron;
	int size;
	
	public static void main(String[] args) {
		Squarelotron sq = new Squarelotron(4);
//		System.out.println(Arrays.deepToString(sq.squarelotron));
		//print what we have created
//		for (int[] a : sq.squarelotron) {
//			System.out.println(Arrays.toString(a));
//		}
		sq.upsideDownFlip(1);
	}
	
	/**The Squarelotron constructor. It fills the 2-dimensional array
	 * with the numbers 1 to n squared, in order. It also sets the size
	 * instance variable to be n.*/
	public Squarelotron(int n){
		this.size = n;
		this.squarelotron = new int[n][n];
		for ( int row = 0; row < size; row ++ ) {
			for( int col = 0; col < squarelotron[row].length; col ++ ) {
				squarelotron[row][col] = (col + 1) + size * row;
			}
		}
//		for (int i = 0; i < squarelotron.length; i++) {//testing result
//			System.out.println(Arrays.toString(squarelotron[i]));
//		}
	}
	
    /**
     * This performs the Upside-Down Flip of the squarelotron. Leaves the original 
     * squarelotron unmodified.
     * @param ring An integer representing the ring to flip upside-down
     * @return A new squarelotron in the flipped state of the original squarelotron
     */
	public Squarelotron upsideDownFlip(int ring) {
		
		Squarelotron newMatrix = new Squarelotron(size);
		
		for(int col = ring - 1; col < size - ring + 1; col ++) {
			newMatrix.squarelotron[ring - 1][col] = 
					this.squarelotron[size - ring][col];
			newMatrix.squarelotron[size - ring][col] = 
					this.squarelotron[ring - 1][col];
		}
		
		for(int row = ring; row < size - ring; row ++) {
			newMatrix.squarelotron[row][ring - 1] =
					this.squarelotron[size - row - 1][ring - 1];
			newMatrix.squarelotron[row][size - ring] =
					this.squarelotron[size - row - 1][size - ring];
		}
		return newMatrix;
	}
	
	/**
     * Thes performs the Main Diagonal Flip of the squarelotron.
     * @param ring An integer representing the ring to flip through the main diagonal
     * @return A new squarelotron in the flipped state of the original squarelotron.
     */
	public Squarelotron mainDiagonalFlip(int ring) {
		
		Squarelotron newMatrix = new Squarelotron(size);
		
		for(int col = ring - 1; col < size - ring + 1; col ++) {
			newMatrix.squarelotron[ring - 1][col] = this.squarelotron[col][ring - 1];
			newMatrix.squarelotron[size - ring][col] = 
					this.squarelotron[col][size - ring];
		}
		
		for(int row = ring; row < size - ring; row ++) {
			newMatrix.squarelotron[row][ring - 1] = this.squarelotron[ring - 1][row];
			newMatrix.squarelotron[row][size - ring] = 
					this.squarelotron[size - ring][row];
		}
		return newMatrix;
	}
	
	/**
     * This performs a rotation of 90 degrees on the squarelotron. It modifies the original
     * squarelotron.
     * @param numberOfTurns Integer representing the number of rotations in
     *                      clockwise (> 0) or
     *                      anticlockwise (< 0)
     */
	public void rotateRight(int numberOfTurns) {
		
		Squarelotron tempMatrix = new Squarelotron(size);
		int num = Math.abs(numberOfTurns);
		
		while(num != 0) {
			for(int row = 0; row < size; row ++) {
				for(int col = 0; col < size; col ++) {
					if(numberOfTurns > 0) {
						tempMatrix.squarelotron[row][col] =
								this.squarelotron[size - 1 - col][row];
					}
					else {
						tempMatrix.squarelotron[row][col] = 
								this.squarelotron[col][size - 1 - row];
					}
				}
			}
			for ( int row = 0; row < size; row ++ ) {
				for( int col = 0; col < squarelotron[row].length; col ++ ) {
					this.squarelotron[row][col] = tempMatrix.squarelotron[row][col];
				}
			}
			num --;
		}
	}
	
	/**
     * This prints the squarelotron. 
     */
	public void printSquarelotron() {
		for ( int row = 0; row < size; row ++ ) {
			for( int col = 0; col < squarelotron[row].length; col ++ ) {
				System.out.print(squarelotron[row][col] + "    ");
			}
			System.out.println();
		}
	}
	
	
}