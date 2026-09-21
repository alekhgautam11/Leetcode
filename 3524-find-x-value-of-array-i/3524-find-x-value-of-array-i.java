class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] nextDp = new long[k];
            int val = num % k;
            
            nextDp[val] += 1;
            
           for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextVal = (r * val) % k;
                    nextDp[nextVal] += dp[r];
                }
            }
            
            dp = nextDp;
            
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }
        
        return result;
    }
}