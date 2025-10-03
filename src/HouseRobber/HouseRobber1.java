package HouseRobber;

import java.util.*;

public class HouseRobber1 {

    /**
     * Method: solve(int ind, int[] nums, int[] dp)
     * --------------------------------------------
     * Computes the maximum money that can be robbed from the first `ind` houses
     * without alerting the police, using *Top-Down Dynamic Programming (Memoization)*.
     *
     * Logic:
     * • Problem:
     *   - Each house i has nums[i] amount of money.
     *   - Constraint: You cannot rob two adjacent houses.
     *   - Goal: Maximize the robbed amount up to house index `ind`.
     *
     * • Recursive Relation:
     *   - At house `ind`, you have two choices:
     *       1. Pick (rob this house):
     *            → Money = nums[ind-1] + solve(ind-2, nums, dp)
     *              (rob current house, skip previous one)
     *       2. Not Pick (skip this house):
     *            → Money = solve(ind-1, nums, dp)
     *
     *   - Therefore:
     *       solve(ind) = max(pick, notPick)
     *
     * • Base Cases:
     *   - If ind == 0 → No houses, return 0.
     *   - If ind == 1 → Only one house, rob it: return nums[0].
     *
     * • Memoization:
     *   - If dp[ind] is already computed (dp[ind] != -1), return dp[ind].
     *   - Otherwise, compute recursively and store result in dp[ind].
     *
     * Example:
     *   Input : nums = [2, 7, 9, 3, 1]
     *   Steps : solve(5)
     *             = max( nums[4] + solve(3), solve(4) )
     *             = max( 1 + 11, 11 )
     *             = 12
     *   Output: 12
     *
     * Complexity:
     * • Time  : O(n) → Each subproblem is computed once and stored.
     * • Space : O(n) → For the dp[] array, plus O(n) recursion stack space.
     *
     * Notes:
     * • Memoization avoids recomputing overlapping subproblems.
     * • For efficiency, a bottom-up (iterative) solution is also possible,
     *   which removes recursion stack overhead.
     */

    public static int solve(int ind,int nums[],int dp[]) {

        if(ind==0) 
            return dp[ind]=0;

        if(ind==1)
            return dp[ind]=nums[0];

        if(dp[ind]!=-1)
            return dp[ind];

        int pick=nums[ind-1]+solve(ind-2,nums,dp);
        int noPick=0+solve(ind-1,nums,dp);

        return dp[ind]=Math.max(pick,noPick);
    }
    
    public static void main(String args[]) {
        
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int nums[]=new int[n];

        for(int i=0;i<n;i++)
            nums[i]=sc.nextInt();

        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);

        System.out.print(solve(n,nums,dp));

        sc.close();
    }
}
