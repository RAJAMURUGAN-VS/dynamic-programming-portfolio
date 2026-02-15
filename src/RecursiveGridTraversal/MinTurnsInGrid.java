package RecursiveGridTraversal;
import java.util.*;

public class MinTurnsInGrid {

    /**
     * Problem: Minimum Turns to Destination in Grid (Using DFS + Dynamic Programming)
     * -------------------------------------------------------------------------------
     * You are given a 2D grid of 1s and 0s, where:
     *   - 1 → A valid cell that can be traversed
     *   - 0 → A blocked cell that cannot be visited
     *
     * From a given starting cell (sr, sc), your task is to reach a destination cell (dr, dc)
     * using only 4-directional movement (up, down, left, right), minimizing the number of
     * direction changes (turns) in the path.
     *
     * Method: solve(int sr, int sc, int dr, int dc, int dir, int[][] grid, int count)
     * -------------------------------------------------------------------------------
     * This method uses a recursive DFS combined with Dynamic Programming (DP) pruning.
     *
     * DP State Definition:
     *   dp[r][c][dir] represents the minimum number of turns required to reach cell (r, c)
     *   when arriving from direction 'dir'.
     *
     * Logic:
     * • If the current cell is out of bounds or blocked, return.
     * • If this cell was already reached earlier with fewer or equal turns from the same direction,
     *   skip processing (DP pruning).
     * • Otherwise, update dp[r][c][dir] with the current turn count.
     * • If the destination cell is reached, update global minimum minCount.
     * • Mark the current cell as visited (grid[r][c] = 2) to avoid cycles.
     * • Explore all 4 possible directions:
     *     - If continuing in the same direction, turn count remains the same.
     *     - If changing direction, increment turn count by 1.
     * • After exploring all paths, backtrack by unmarking the current cell.
     *
     * Main Function:
     * • Reads grid dimensions and grid values.
     * • Reads source and destination coordinates (1-based index).
     * • Converts them to 0-based index.
     * • Initializes dp array with Integer.MAX_VALUE.
     * • Calls solve() with initial direction = -1 and turn count = 0.
     * • Prints the minimum number of turns required.
     *
     * Example:
     * Input:
     * 6 6
     * 1 1 1 0 1 1
     * 1 0 1 0 1 0
     * 1 0 1 1 1 0
     * 1 0 0 0 1 1
     * 1 1 1 1 1 1
     * 0 0 0 0 0 1
     * 1 1
     * 6 6
     *
     * Grid:
     * [1, 1, 1, 0, 1, 1]
     * [1, 0, 1, 0, 1, 0]
     * [1, 0, 1, 1, 1, 0]
     * [1, 0, 0, 0, 1, 1]
     * [1, 1, 1, 1, 1, 1]
     * [0, 0, 0, 0, 0, 1]
     *
     * Optimal Path (minimum turns):
     * (0,0) ↓ (1,0) ↓ (2,0) ↓ (3,0) ↓ (4,0)
     *       → (4,1) → (4,2) → (4,3) → (4,4) → (4,5)
     *                                             ↓ (5,5)
     *
     * Turns:
     * Down → Right at (4,0)
     * Right → Down at (4,5)
     *
     * Output:
     * 2
     *
     * Complexity:
     * • Time  : O(R × C × 4)
     *           Each cell is visited at most 4 times (one per direction) due to DP pruning.
     *
     * • Space : O(R × C × 4)
     *           For DP array and recursion stack.
     *
     * Advantage over pure DFS:
     * • Avoids exponential exploration.
     * • Efficient for large grids.
     */

    public static int dp[][][];
    public static int minCount=Integer.MAX_VALUE;
    public static int dx[]={0,1,0,-1};
    public static int dy[]={1,0,-1,0};
    
    public static void solve(int sr,int sc,int dr,int dc,int dir,int grid[][],int count) {
        
        if(sr<0 || sc<0 || sr>=grid.length || sc>=grid[0].length || grid[sr][sc]!=1)
            return;
         
        if(dir!=-1 && dp[sr][sc][dir]<=count)
            return;
            
        if(dir!=-1)
            dp[sr][sc][dir]=count;
            
        if(sr==dr && sc==dc) {
            minCount=Math.min(minCount,count);
            return;
        }
        
        grid[sr][sc]=2;
        
        for(int i=0;i<4;i++) {
            int currCount=(dir==-1 || i==dir)?count:count+1;
            solve(sr+dx[i],sc+dy[i],dr,dc,i,grid,currCount);
        }
        
        grid[sr][sc]=1;
    }
    
	public static void main(String args[]) {

        Scanner scan=new Scanner(System.in);
        int R=scan.nextInt();
        int C=scan.nextInt();

        int grid[][]=new int[R][C];
        dp=new int[R][C][4];

        for(int i=0;i<R;i++)
            for(int j=0;j<C;j++)
                grid[i][j]=scan.nextInt();
                
        for(int i=0;i<R;i++)
            for(int j=0;j<C;j++)
                Arrays.fill(dp[i][j],Integer.MAX_VALUE);

        int sr=scan.nextInt()-1;
        int sc=scan.nextInt()-1;
        int dr=scan.nextInt()-1;
        int dc=scan.nextInt()-1;

        solve(sr,sc,dr,dc,-1,grid,0);

        System.out.print(minCount);

        scan.close();
    }
}