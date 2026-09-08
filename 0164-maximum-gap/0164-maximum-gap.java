import java.util.Arrays;

class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (min == max) {
            return 0;
        }

        int n = nums.length;
        int bucketSize = Math.max(1, (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        for (int num : nums) {
            int idx = (num - min) / bucketSize;
            bucketMin[idx] = Math.min(bucketMin[idx], num);
            bucketMax[idx] = Math.max(bucketMax[idx], num);
        }

        int maxGap = 0;
        int previousMax = min;

        for (int i = 0; i < bucketCount; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE) {
                continue; // Skip empty buckets
            }
            maxGap = Math.max(maxGap, bucketMin[i] - previousMax);
            previousMax = bucketMax[i];
        }

        return maxGap;
    }
}