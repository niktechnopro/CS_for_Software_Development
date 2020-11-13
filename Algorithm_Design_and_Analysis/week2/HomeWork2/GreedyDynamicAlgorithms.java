
import java.util.*;

public class GreedyDynamicAlgorithms {
    
    public static void print(String str){
        System.out.println(str);
    }

	/**
	 * Goal: find the smallest number of red intervals to select, such that
	 * every blue interval overlaps with at least one of the selected red intervals.
	 * Output this number
	 * 
	 * @param red - the list of red intervals
	 * @param blue - the list blue intervals
	 * @return
	 */
	public static int optimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue) {
        Interval.sortByFinishTime(red);
        Interval.sortByStartTime(blue);
        
        //just to help visualize is - print intervals
//         for(Interval inter : red){
//             print("red start: " + inter.start);
//             print("red finish: " + inter.finish);
//         }
//         for(Interval inter : blue){
//             print("blue start: " + inter.start);
//             print("blue finish: " + inter.finish);
//         }
        //just to help visualize is - print intervals
		//TODO
        int i = 0, j = 0, count = 0;
        while(i < blue.size() && j < red.size()){
            if(!isIntersected(blue.get(i), red.get(j))){//if blue and red do not intersect - jump to next iteration
//                 print("blue start: " + blue.get(i).start + " blue fisish: " + blue.get(i).finish);
//                 print("red start: " + red.get(j).start + " red finish: " + red.get(j).finish);
                j++;
                continue;//causes loop to jump to next iteration;
            }
            //now blue[i] and red[j] are intersected
            
            //for the next blue[i], find the last j so that red[j] overlaps with blue[i]
            while(j < red.size() && isIntersected(blue.get(i), red.get(j))){
                j++;
            }
            
            //j-1 is the last on that intersected
            count++;
            while(isIntersected(blue.get(i), red.get(j -1))){//remove all the blue[i] that red[j -1] overlaps
                i++;
                if(i == blue.size()){
                    return count;
                }
            }
        }
        
        if((i < blue.size())) return -1;
        print("count: " + count);
		return count;
	}
	
    //to check if 2 intervals red and blue are intersected
    public static boolean isIntersected(Interval blue, Interval red){
        if(red.start <= blue.start){
            return red.finish >= blue.start;//true if red overlaps with blue
        }
        else{
            return red.start <= blue.finish;//true if blur overlaps with red
        }
    }
    
    
	/**
	 * Goal: find any path of lowest cost from the top-left of the grid (grid[0][0])
	 * to the bottom right of the grid (grid[m-1][n-1]).  Output this sequence of directions
	 * 
	 * @param grid - the 2d grid containing the cost of each location in the grid.
	 * @return
	 */
	public static List<Direction> optimalGridPath(int[][] grid) {
		//TODO
        int m = grid.length, n = grid[0].length, i = 0, j = 0;//m - rows, n - columns, i - count for DOWN, j - count for RIGHT 
//         print("grid m: " + m + " grid n " + n);
        int [][] min = new int[m][n];
        List<Direction> direction = new ArrayList<>();
        print("min: " + min);
        int minPath = minPath(grid, 0, 0, min);//start at the position 0,0
        while (true){
            if(i == m - 1 && j == n - 1){
                 break;
            }
            if(!isValidSquare(grid, i, j+1) || (isValidSquare(grid, i + 1, j) && (min[i+1][j] < min[i][j+1]))){
                direction.add(Direction.DOWN);
                i++;
            }
            else{
                direction.add(Direction.RIGHT);
                j++;
            }
        }
//         print("direction: " + direction);
		return direction;
	}
	
	/**
	 * A simple Direction enum
	 * directions can be either DOWN or RIGHT
	 * You will output a list of these in the grid-path problem
	 */
	public static enum Direction {
		DOWN, RIGHT
	}
    
    public static int minPath(int[][] grid, int row, int column, int[][]min){
        boolean isValidSquare = isValidSquare(grid, row, column);
        if(!isValidSquare){
            return Integer.MAX_VALUE;
        }
        
        if(row == grid.length - 1 && column == grid[0].length - 1){//very bottom right
            min[row][column] = grid[row][column];
            return min[row][column];
        }
        
        if(min[row][column] == 0){
            min[row][column] = grid[row][column] + Math.min(minPath(grid, row, column + 1, min), minPath(grid, row+1, column, min));
        }
        return min[row][column];
    }
    
    public static boolean isValidSquare(int[][] grid, int row, int column){
        return (row < grid.length) && (column < grid[0].length);
    }
	/**
	 * A private Interval class to help with the interval question
	 */
	public static class Interval {
		
		int start;
		int finish;
		
		public Interval(int start, int finish) {
			this.start = start;
			this.finish = finish;
		}
		
		/**
		 * sorts a list of intervals by start time, you are free to use this on the first question
		 */
		public static void sortByStartTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.start - i2.start;
				}
			});
		}
		
		/**
		 * sorts a list of intervals by finish time, you are free to use this on the first question
		 */
		public static void sortByFinishTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.finish - i2.finish;
				}
			});
		}
	}
	
}
