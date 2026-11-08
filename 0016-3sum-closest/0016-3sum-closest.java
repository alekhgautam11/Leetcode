class Solution {
    public int threeSumClosest(int[] nums, int target) {
        java.util.Arrays.sort(nums);
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

           int minSum = nums[i] + nums[i + 1] + nums[i + 2];
            if (minSum > target) {
                if (Math.abs(minSum - target) < Math.abs(closestSum - target)) {
                    closestSum = minSum;
                }
                break;    }

          int maxSum = nums[i] + nums[n - 1] + nums[n - 2];
            if (maxSum < target) {
                if (Math.abs(maxSum - target) < Math.abs(closestSum - target)) {
                    closestSum = maxSum;
                }
                continue; // Move to a larger i
            }

            // Standard two-pointer search
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                if (currentSum == target) {
                    return target;
                }

                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                if (currentSum < target) {
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                } else {
                    right--;
                   while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
        }

        return closestSum;
    }
}