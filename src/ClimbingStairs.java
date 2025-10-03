import java.util.*;

class ClimbingStairs {

    /**
     * Method: solve(int n, int[] dp)
     * ------------------------------
     * Computes the number of distinct ways to climb n steps using 
     * *Top-Down Dynamic Programming (Memoization)*.
     *
     * Logic:
     * • Base Cases:
     *   - If n == 1 → only 1 way to climb 1 step.
     *   - If n == 2 → 2 ways to climb 2 steps (1+1 or 2).
     *
     * • Memoization:
     *   - If dp[n] is already computed (non-zero), return dp[n] directly.
     *   - Otherwise, compute it recursively as:
     *       ways(n) = ways(n-1) + ways(n-2)
     *     and store the result in dp[n] for reuse.
     *
     * Example:
     *   Input : n = 3
     *   Steps : ways(3) = ways(2) + ways(1)
     *                  = 2 + 1
     *   Output: 3
     *
     * Complexity:
     * • Time  : O(n) → Each state from 1..n is computed at most once.
     * • Space : O(n) → For the dp[] array, plus O(n) recursion stack space.
     */

    public static int solve(int n,int dp[]) {

        if(n==1 || n==2)
            return dp[n]=n;

        if(dp[n]!=0)
            return dp[n];

        return dp[n]=solve(n-1,dp)+solve(n-2,dp);        
    }

    public static void main(String args[]) {
        
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int dp[]=new int[n+1];

        System.out.print(solve(n,dp));

        sc.close();
    }
}