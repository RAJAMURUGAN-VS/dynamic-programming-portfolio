import java.util.*;

public class SubsequenceWithSumK {

    /**
     * Method: solve(int ind, int[] value, int target, int[][] dp)
     * -----------------------------------------------------------
     * Determines whether a subsequence exists in the given array whose sum equals
     * the specified target `k`, using **Top-Down Dynamic Programming (Memoization)**.
     *
     * Logic:
     * • Base Cases:
     *   - If target == 0 → return true (an empty subsequence sums to 0).
     *   - If ind == 0 → return true only if value[0] == target.
     *
     * • Memoization:
     *   - If dp[ind][target] is already computed (non-zero), return its stored boolean state.
     *   - Otherwise, explore two possibilities:
     *       1. **Pick** the current element if value[ind] <= target:
     *            → Recursively check for (target - value[ind]) with index (ind - 1).
     *       2. **Not Pick** the current element:
     *            → Recursively check the previous index with the same target.
     *     If either returns true, store 1 (true) in dp[ind][target]; else, store 2 (false).
     *
     * Example:
     *   Input : arr = [10, 1, 2, 7, 6, 1, 5], k = 8
     *   Steps :
     *     solve(6, arr, 8) checks subsets including or excluding arr[6] = 5
     *     → eventually finds valid combinations like [2, 6] or [1, 7]
     *   Output: true
     *
     * Complexity:
     * • Time  : O(N * K) → Each subproblem (index, target) is computed once.
     * • Space : O(N * K) → For the dp[][] table + recursion stack space.
     *
     * Notes:
     * • This implementation avoids redundant recomputation by caching results in dp[][].
     * • A bottom-up tabulation approach can be used for iterative optimization and reduced stack usage.
     */

    public static boolean solve(int ind,int value[],int target,int dp[][]) {

        if(target==0) {
            dp[ind][target]=1;
            return true;
        }

        if(ind==0) {
            if(value[ind]==target) {
                dp[ind][target]=1;
                return true;
            }
            dp[ind][target]=2;
            return false;
        }

        if(dp[ind][target]!=0) 
            return dp[ind][target]==1;

        boolean pick=false;

        if(value[ind]<=target) 
            pick=solve(ind-1,value,target-value[ind],dp);
            
        if(pick) {
            dp[ind][target]=1;
            return true;
        }   

        boolean noPick=solve(ind-1,value,target,dp);
        dp[ind][target]=noPick?1:2;

        return noPick;
    } 
    
    public static void main(String args[]) {

        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int value[]=new int[n];

        for(int i=0;i<n;i++)
            value[i]=sc.nextInt();

        int dp[][]=new int[n][k+1];

        System.out.print(solve(n-1,value,k,dp));

        sc.close();
    }
}
