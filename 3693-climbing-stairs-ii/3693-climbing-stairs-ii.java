class Solution {
    public int climbStairs(int n, int[] costs) {
       int[] dp = new int[n + 1];
        
        // Base case: step 0 has 0 cost
        dp[0] = 0;
        
        for (int j = 1; j <= n; j++) {
            dp[j] = Integer.MAX_VALUE;
            
            for (int k = 1; k <= 3; k++) {
                if (j - k >= 0) {
                    int jumpCost = k * k;
                    int stepCost = costs[j - 1]; // costs array is 0-indexed
                    dp[j] = Math.min(dp[j], dp[j - k] + stepCost + jumpCost);
                }
            }
        }
        
        return dp[n];
    }
}