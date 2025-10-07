import java.util.*;

public class CoinChange {

    /**
     * Method: solve(int ind, int amount, int[] coins, int[][] dp)
     * ------------------------------------------------------------
     * Finds the *minimum number of coins* required to form the given `amount`
     * using an infinite supply of coins of various denominations.
     * Uses **Top-Down Dynamic Programming (Memoization)** to avoid redundant calculations.
     *
     * Logic:
     * • At each index `ind`, we have two choices:
     *    1. **Pick** the current coin `coins[ind]` (if its value ≤ `amount`):
     *         → Include this coin, reduce the target amount by `coins[ind]`,  
     *           and **recurse on the same index** (since coins can be reused infinitely).
     *         → Computed as: 1 + solve(ind, amount - coins[ind], coins, dp)
     *
     *    2. **Skip** the current coin:
     *         → Move to the previous index by calling:
     *           solve(ind - 1, amount, coins, dp)
     *
     * • Base Case:
     *    If `ind == 0` (only one denomination left):
     *      - If `amount` is divisible by `coins[0]`, return `amount / coins[0]`
     *        (use this coin repeatedly to reach the total).
     *      - Otherwise, return a large sentinel value (e.g., 1e9) to indicate impossibility.
     *
     * • Memoization:
     *    - Before computing a state `(ind, amount)`, check if `dp[ind][amount]` is already filled.
     *      If so, return that value directly to save computation time.
     *
     * Example:
     *   coins = [1, 2, 5], amount = 11
     *   Steps:
     *     - Choose 5 → solve(2, 6)
     *     - Choose 5 → solve(2, 1)
     *     - Choose 1 → solve(2, 0)
     *   Result: Minimum coins = 3 (5 + 5 + 1)
     *
     * Complexity:
     * • Time  : O(n * amount)
     *           → Each state `(ind, amount)` is computed once and stored in `dp`.
     * • Space : O(n * amount)
     *           → For the memoization table, plus O(amount) recursion stack space.
     *
     * Notes:
     * • Returns 1e9 for impossible cases — the caller interprets this as `-1`.
     * • This top-down approach ensures overlapping subproblems are solved only once,
     *   improving efficiency over a naive recursive solution.
     */

    public static int solve(int ind,int amount,int coins[],int dp[][]) {

        if(ind==0) {
            if(amount%coins[ind]==0)
                return dp[ind][amount]=amount/coins[ind];
            return dp[ind][amount]=(int)(1e9);
        }

        if(dp[ind][amount]!=-1)
            return dp[ind][amount];

        int pick=(int)(1e9);

        if(coins[ind]<=amount)
            pick=1+solve(ind,amount-coins[ind],coins,dp);

        int noPick=solve(ind-1,amount,coins,dp);

        return dp[ind][amount]=Math.min(pick,noPick);
    }
    
    public static void main(String args[]) {
        
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int amount=sc.nextInt();
        int coins[]=new int[n];

        for(int i=0;i<n;i++)
            coins[i]=sc.nextInt();

        int dp[][]=new int[n][amount+1];

        for(int row[] : dp)
            Arrays.fill(row,-1);

        int res=solve(n-1,amount,coins,dp);

        System.out.print(res==(int)(1e9)?-1:res);

        sc.close();
    }
}
