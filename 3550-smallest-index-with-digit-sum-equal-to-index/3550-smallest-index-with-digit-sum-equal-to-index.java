class Solution {
    public int smallestIndex(int[] nums) {
      
        int limit = Math.min(nums.length, 28);
        
        for (int i = 0; i < limit; i++) {
            int val = nums[i];
           
            int sum = val % 10 + (val / 10) % 10 + (val / 100) % 10 + (val / 1000);
            if (sum == i) {
                return i;
            }
        }
        return -1;
    }
}