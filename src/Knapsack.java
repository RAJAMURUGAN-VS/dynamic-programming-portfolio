import java.util.*;

class Knapsack {

    /**
     * Method: solve(int ind, int capacity, int[] val, int[] wt, int[][] dp)
     * --------------------------------------------------------------------
     * Solves the **0/1 Knapsack Problem** using *Top-Down Dynamic Programming (Memoization)*.
     * The goal is to maximize the total value of selected items without exceeding
     * the given knapsack capacity.
     *
     * Logic:
     * • Base Cases:
     *   - If capacity == 0 → return 0 (no remaining capacity, no value can be added).
     *   - If ind == 0:
     *       → If wt[0] ≤ capacity → return val[0] (include the first item).
     *       → Otherwise, return 0 (cannot include item due to weight constraint).
     *
     * • Memoization:
     *   - If dp[ind][capacity] is already computed (not -1), return its stored value.
     *   - Otherwise, consider two possibilities:
     *       1. **Pick** the current item (if wt[ind] ≤ capacity):
     *            → value = val[ind] + solve(ind - 1, capacity - wt[ind], val, wt, dp)
     *       2. **Not Pick** the current item:
     *            → value = solve(ind - 1, capacity, val, wt, dp)
     *     The result is the maximum of both choices.
     *
     * Example:
     *   Input : val = [60, 100, 120], wt = [10, 20, 30], W = 50
     *   Steps :
     *     solve(2, 50)
     *       → pick = 120 + solve(1, 20)
     *       → noPick = solve(1, 50)
     *       → returns 220
     *   Output: 220
     *
     * Complexity:
     * • Time  : O(N * W) → Each state (index, capacity) computed once.
     * • Space : O(N * W) → For the dp[][] table, plus recursion stack space.
     *
     * Notes:
     * • This approach eliminates redundant subproblems using memoization.
     * • For large input sizes, a bottom-up tabulation approach is often preferred
     *   to improve space efficiency and remove recursion depth limitations.
     */

    public static int solve(int ind,int capacity,int val[],int wt[],int dp[][]) {
        
        if(capacity==0) {
            dp[ind][capacity]=0;
            return 0;
        }
            
        if(ind==0) {
            if(wt[ind]<=capacity) {
                dp[ind][capacity]=val[ind];
                return val[ind];
            }
            else {
                dp[ind][capacity]=0;
                return 0;
            }
        }
        
        if(dp[ind][capacity]!=-1)
            return dp[ind][capacity];
        
        int pick=0;
        
        if(wt[ind]<=capacity)
            pick=val[ind]+solve(ind-1,capacity-wt[ind],val,wt,dp);
        
        int noPick=0+solve(ind-1,capacity,val,wt,dp);
        
        return dp[ind][capacity]=Math.max(pick,noPick);
    }

    public static  void main(String args[]) {
        
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int capacity=sc.nextInt();
        int val[]=new int[n];
        int wt[]=new int[n];

        for(int i=0;i<n;i++)
            val[i]=sc.nextInt();

        for(int i=0;i<n;i++)
            wt[i]=sc.nextInt();

        int dp[][]=new int[n][capacity+1];
        
        for(int row[] : dp)
            Arrays.fill(row,-1);
            
        System.out.print(solve(n-1,capacity,val,wt,dp));

        sc.close();
    }
}