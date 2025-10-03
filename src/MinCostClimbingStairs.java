import java.util.*;

class Solution {

    /**
     * Method: solve(int n, int[] cost, int[] dp)
     * ------------------------------------------
     * Computes the minimum cost required to reach the top of the staircase
     * using *Top-Down Dynamic Programming (Memoization)*.
     *
     * Logic:
     * • Problem Statement:
     *   - Given a cost[] array where cost[i] is the cost of stepping on the ith stair,
     *     you can climb either 1 or 2 steps at a time.
     *   - You may start at index 0 or 1.
     *   - Goal: Find the minimum cost to reach the top (beyond the last stair).
     *
     * • Recursive Relation:
     *   - To reach step 'n', you can come either:
     *       → from (n-1) by paying cost[n-1], OR
     *       → from (n-2) by paying cost[n-2].
     *   - Therefore:
     *       solve(n) = min( cost[n-1] + solve(n-1),
     *                       cost[n-2] + solve(n-2) )
     *
     * • Base Case:
     *   - If n <= 1 → No cost is required (return 0),
     *     since starting at step 0 or 1 is free.
     *
     * • Memoization:
     *   - If dp[n] is already computed (dp[n] != -1), return dp[n].
     *   - Otherwise, compute using the recursive relation and store it in dp[n].
     *
     * Example:
     *   Input : cost = [10, 15, 20]
     *   Steps : Start at index 1 (cost=15), jump 2 steps → reach top.
     *   Output: 15
     *
     * Complexity:
     * • Time  : O(n) → Each subproblem (step) from 0..n is computed once.
     * • Space : O(n) → For the dp[] array, plus O(n) recursion stack space.
     *
     * Notes:
     * • This approach prevents recomputation by caching results.
     * • A bottom-up (iterative) DP version is also possible to save recursion space.
     */

    public static int solve(int n,int cost[],int dp[]) {

        if(n<=1)
            return 0;

        if(dp[n]!=-1)
            return dp[n];

        int oneStep=cost[n-1]+solve(n-1,cost,dp);
        int twoStep=cost[n-2]+solve(n-2,cost,dp);

        return dp[n]=Math.min(oneStep,twoStep);
    }

    public static void main(String args[]) {
        
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int cost[]=new int[n];

        for(int i=0;i<n;i++)
            cost[i]=sc.nextInt();

        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        
        System.out.print(solve(n,cost,dp));
        sc.close();
    }
}