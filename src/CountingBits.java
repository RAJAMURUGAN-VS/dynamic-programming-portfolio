import java.util.*;

public class CountingBits {

    /**
     * Method: solve(int n, int[] dp)
     * ------------------------------
     * Computes the number of 1's in the binary representation of all integers
     * from 0 to n using *Dynamic Programming (Bottom-Up / Tabulation)*.
     *
     * Logic:
     * • Base Case:
     *   - dp[0] = 0 → 0 has no 1's in binary.
     *
     * • Dynamic Programming:
     *   - For each i from 1 to n:
     *       dp[i] = dp[i >> 1] + (i & 1)
     *     Explanation:
     *       - i >> 1 shifts i right by 1 (dividing by 2), giving dp[i/2]
     *       - (i & 1) adds 1 if the least significant bit is 1
     *     This efficiently builds dp[i] from previously computed results.
     *
     * Example:
     *   Input : n = 5
     *   Steps :
     *     dp[0] = 0
     *     dp[1] = dp[0] + 1 = 1
     *     dp[2] = dp[1] + 0 = 1
     *     dp[3] = dp[1] + 1 = 2
     *     dp[4] = dp[2] + 0 = 1
     *     dp[5] = dp[2] + 1 = 2
     *   Output: [0, 1, 1, 2, 1, 2]
     *
     * Complexity:
     * • Time  : O(n) → Each dp[i] is computed once using previously computed values.
     * • Space : O(n) → For the dp[] array storing counts for all 0..n.
     *
     * Notes:
     * • This approach avoids recalculating the number of 1's for each number individually.
     * • Uses bit manipulation (i >> 1 and i & 1) for efficient computation.
     */

    public static int[] solve(int n,int dp[]) {

        for(int i=1;i<=n;i++) 
            dp[i]=dp[i>>1]+(i&1); 
        
        return dp;
    }
    public static void main(String args[]) {
        
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int dp[]=new int[n+1];

        solve(n,dp);

        for(int i=0;i<=n;i++)
            System.out.print(dp[i]+" ");

        sc.close();
    }
}