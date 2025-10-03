import java.util.*;

class Fibonacci {

    /**
     * Method: solve(int n, int[] dp)
     * ------------------------------
     * Computes the nth Fibonacci number using *Top-Down Dynamic Programming (Memoization)*.
     *
     * Logic:
     * • Base Case:
     *   - If n <= 1 → Fibonacci value is equal to n (Fib(0) = 0, Fib(1) = 1).
     *
     * • Memoization:
     *   - If dp[n] is already computed (non-zero), return dp[n] directly.
     *   - Otherwise, compute it recursively as:
     *       Fib(n) = Fib(n-1) + Fib(n-2)
     *     and store the result in dp[n] for reuse.
     *
     * Example:
     *   Input : n = 5
     *   Steps : Fib(5) = Fib(4) + Fib(3)
     *                  = (Fib(3) + Fib(2)) + (Fib(2) + Fib(1))
     *                  = ...
     *   Output: 5
     *
     * Complexity:
     * • Time  : O(n) → Each state from 0..n is computed once and stored.
     * • Space : O(n) → For the dp[] array, plus O(n) recursion stack space.
     *
     * Notes:
     * • This implementation avoids redundant recomputation by caching results.
     * • For large n, an iterative bottom-up (tabulation) approach is often preferred 
     *   to eliminate recursion stack overhead.
     */

    public static int solve(int n,int dp[]) {

        if(n<=1)
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