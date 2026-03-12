import java.util.*;

public class MinimizeTheDifference {

    /**
     * Problem: Minimize the Difference Between Target and Chosen Elements
     * -------------------------------------------------------------------
     * You are given a matrix of size R × C and a target value K.
     *
     * From each row of the matrix, you must choose exactly one element.
     * After choosing one element from every row, calculate the sum of the
     * selected elements.
     *
     * Your goal is to minimize the absolute difference between this sum
     * and the target value K.
     *
     * Difference = |sum − K|
     *
     * Method: solve(int row, int sum, int K, int R, int C, int[][] grid)
     * ------------------------------------------------------------------
     * This method uses Depth-First Search (DFS) combined with Dynamic
     * Programming (DP) pruning to efficiently explore all possible
     * selections.
     *
     * DP State Definition:
     * dp[row][sum] indicates whether we have already processed a state
     * where we reached row 'row' with the current accumulated sum 'sum'.
     *
     * If the same state appears again, it is skipped to avoid redundant
     * computations.
     *
     * Logic:
     * • If all rows are processed (row == R):
     *      - Compute |sum − K|
     *      - Update the global minimum difference (minDiff).
     *
     * • If this state (row, sum) has already been visited:
     *      - Skip further processing (DP pruning).
     *
     * • Otherwise:
     *      - Mark the state as visited.
     *      - Try choosing each element from the current row.
     *      - Recursively move to the next row while updating the sum.
     *
     * Main Function:
     * • Reads matrix dimensions R and C.
     * • Reads the grid values.
     * • Reads the target value K.
     * • Initializes:
     *      - minDiff = Integer.MAX_VALUE
     *      - dp array to store visited states.
     * • Calls solve() starting from:
     *      row = 0, sum = 0.
     * • Prints the minimum difference obtained.
     *
     * Example:
     *
     * Input:
     * 3 3
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 13
     *
     * Matrix:
     * [1, 2, 3]
     * [4, 5, 6]
     * [7, 8, 9]
     *
     * Possible selections:
     * 1 + 4 + 7 = 12
     * 2 + 4 + 7 = 13
     * 3 + 5 + 8 = 16
     * 3 + 6 + 9 = 18
     *
     * Closest sum to target 13 is:
     * 13
     *
     * Difference:
     * |13 − 13| = 0
     *
     * Output:
     * 0
     *
     * Complexity:
     * • Time  : O(R × MaxSum × C)
     *           Because each state (row, sum) is processed once due to DP.
     *
     * • Space : O(R × MaxSum)
     *           For the DP array and recursion stack.
     *
     * Advantage over brute force:
     * • Avoids exploring duplicate states.
     * • Significantly reduces exponential combinations.
     */
  
    public static boolean dp[][];
    public static int minDiff;

    public static void solve(int row,int sum,int K,int R,int C,int grid[][]) { 
        if(row==R) {
            minDiff=Math.min(minDiff,Math.abs(sum-K)); 
            return;
        }

        if(sum-K>minDiff)
            return;

        if(dp[row][sum])
            return;

        dp[row][sum]=true;

        for(int col=0;col<C;col++)
            solve(row+1,sum+grid[row][col],K,R,C,grid); 
    }
    public static void main(String args[]) {

        Scanner sc=new Scanner(System.in);
        int R=sc.nextInt();
        int C=sc.nextInt();
        int grid[][]=new int[R][C];

        for(int i=0;i<R;i++)
            for(int j=0;j<C;j++)
              grid[i][j]=sc.nextInt();

        int target=sc.nextInt();
        
        minDiff=Integer.MAX_VALUE;
        dp=new boolean[71][5000];
        
        solve(0,0,target,R,C,grid);

        System.out.print(minDiff);
        sc.close();
    }
}
