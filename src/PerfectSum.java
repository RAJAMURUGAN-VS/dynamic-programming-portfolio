import java.util.*;

public class PerfectSum {

    /**
     * Method: solve(int ind, int[] nums, int target, int[][] dp)
     * ----------------------------------------------------------
     * Counts the number of subsets within the given array whose sum equals
     * the specified target, using **Top-Down Dynamic Programming (Memoization)**.
     *
     * Logic:
     * • Base Case:
     *   - If ind == 0:
     *       → If both target == 0 and nums[0] == 0 → return 2 (two subsets: include or exclude zero).
     *       → If target == 0 or target == nums[0] → return 1 (either empty set or single-element subset).
     *       → Otherwise, return 0 (no valid subset).
     *
     * • Memoization:
     *   - If dp[ind][target] is already computed (not -1), return its value.
     *   - Otherwise, explore two choices:
     *       1. **Pick** the current element (if nums[ind] ≤ target):
     *            → Recursively count subsets including nums[ind], i.e., solve(ind - 1, nums, target - nums[ind], dp).
     *       2. **Not Pick** the current element:
     *            → Recursively count subsets excluding nums[ind], i.e., solve(ind - 1, nums, target, dp).
     *     The total count is the sum of both results.
     *
     * Example:
     *   Input : nums = [5, 2, 3, 10, 6, 8], target = 10
     *   Steps :
     *     solve(5, nums, 10)
     *       → includes combinations like [5, 2, 3], [2, 8], and [10]
     *   Output: 3
     *
     * Complexity:
     * • Time  : O(N * Target) → Each subproblem (index, target) is computed once.
     * • Space : O(N * Target) → For the dp[][] table, plus recursion stack space.
     *
     * Notes:
     * • This approach efficiently counts all possible subsets without recomputation.
     * • For larger datasets, an iterative bottom-up (tabulation) method can be used to
     *   improve performance and avoid recursion depth limitations.
     */

    public static int solve(int ind,int nums[],int target,int dp[][]) {
        
        if(ind==0) {
            if(target==0 && nums[0]==0) {
                dp[ind][target]=2;
                return 2;
            }
            else if(target==0 || target==nums[0]) {
                dp[ind][target]=1;
                return 1;
            }
            else {
                dp[ind][target]=0;
                return 0;
            }
        }
        
        if(dp[ind][target]!=-1) 
            return dp[ind][target];
        
        int pick=0;
        
        if(nums[ind]<=target)
            pick=solve(ind-1,nums,target-nums[ind],dp);
            
        int noPick=solve(ind-1,nums,target,dp);
        
        return dp[ind][target]=pick+noPick;
    }
    
    public static void main(String args[]) {

        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int target=sc.nextInt();

        int nums[]=new int[n];

        for(int i=0;i<n;i++)
            nums[i]=sc.nextInt();

        int dp[][]=new int[n][target+1];
        
        for(int row[] : dp)
            Arrays.fill(row,-1);
            
        System.out.print(solve(n-1,nums,target,dp));

        sc.close();
    }
}
