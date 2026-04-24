package RecursiveGridTraversal;

import java.util.*;

class MaxSubArrayWithFlip {

  /**
    * Method: getMaxSub(int ind, int[] a, int N, int flip, int dir, int[][][] dp)
    * --------------------------------------------------------------------------
    * Finds the maximum length of a subarray starting from index `ind` such that:
    * • Elements follow a consistent sign direction (non-negative or negative).
    * • At most one sign flip operation is allowed.
    *
    * Logic:
    * • Base Case:
    *   - If ind == N, return 0 (end of array reached).
    *
    * • Memoization:
    *   - If dp[ind][dir][flip] is already computed (not -1), return it.
    *
    * • Choices at each index:
    *
    *   1) Flip Case (only if flip == 1):
    *      - Flip current element’s sign, which changes direction.
    *      - Include this element in the subarray.
    *      - Move to next index with flip = 0.
    *
    *      count = 1 + getMaxSub(ind+1, a, N, 0, opposite(dir), dp)
    *
    *   2) No Flip Case:
    *      - Check if current element matches the required direction:
    *
    *        If matches:
    *          - Include element
    *          count = 1 + getMaxSub(ind+1, a, N, flip, dir, dp)
    *
    *        If not matches:
    *          - Skip element
    *          count = getMaxSub(ind+1, a, N, flip, dir, dp)
    *
    * • Store the result in dp[ind][dir][flip] and return it.
    *
    * Parameters:
    * • ind  : current index in array
    * • a[]  : input array
    * • N    : size of array
    * • flip : 1 if flip is available, 0 if already used
    * • dir  : direction
    *          1 -> non-negative sequence
    *          0 -> negative sequence
    * • dp   : memoization table
    *
    * Example:
    *   Input : [1, -2, -3, 4]
    *   Operation:
    *     Flip 4 -> -4 -> sequence becomes [1, -2, -3, -4]
    *   Output: 4
    *
    * Complexity:
    * • Time  : O(N * 2 * 2) -> each state computed once
    * • Space : O(N * 2 * 2) -> dp table + recursion stack
    *
    * Notes:
    * • Uses top-down dynamic programming (memoization)
    * • Only one flip is allowed
    * • Direction tracking ensures valid subarray formation
    */

  public static int getMaxSub(int ind,int a[],int N,int flip,int dir,int dp[][][]) {

    //Out-of-bound (child-node -> end of recursion) [base case]
    if(ind==N) {
      return 0;
    }

    if(dp[ind][dir][flip]!=-1) {
      return dp[ind][dir][flip];
    } 

    int count=0;

    //flip
    if(flip==1) {
      count=Math.max(count,1+getMaxSub(ind+1,a,N,0,(dir==1?0:1),dp));
    }

    //no flip
    if((dir==1 && a[ind]>=0) || (dir==0 && a[ind]<0)) 
      count=Math.max(count,1+getMaxSub(ind+1,a,N,flip,dir,dp));
    else
      count=Math.max(count,getMaxSub(ind+1,a,N,flip,dir,dp));

    return dp[ind][dir][flip]=count;
  }

  public static void main(String args[]) {

    Scanner sc=new Scanner(System.in);
    int N=sc.nextInt();

    int a[]=new int[N];

    for(int i=0;i<N;i++)
      a[i]=sc.nextInt();

    int dp[][][]=new int[N][2][2];

    for(int i=0;i<N;i++)
        for(int j=0;j<2;j++)
          Arrays.fill(dp[i][j],-1);

    int maxCount=0;

    for(int i=0;i<N;i++) {
      maxCount=Math.max(maxCount,1+getMaxSub(i+1,a,N,1,(a[i]>=0?1:0),dp));
    }

    System.out.print(maxCount);  

    sc.close();
  }
}

