import java.util.Arrays;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

       int zeroIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }

        boolean isIncRotation = true;
        for (int i = 0; i < n; i++) {
            if (nums[(zeroIdx + i) % n] != i) {
                isIncRotation = false;
                break;
            }
        }

       int maxIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == n - 1) {
                maxIdx = i;
                break;
            }
        }

        boolean isDecRotation = true;
        for (int i = 0; i < n; i++) {
            if (nums[(maxIdx + i) % n] != n - 1 - i) {
                isDecRotation = false;
                break;
            }
        }

        if (!isIncRotation && !isDecRotation) {
            return -1;
        }

        int minOps = Integer.MAX_VALUE;

        if (isIncRotation) {
            minOps = Math.min(minOps, zeroIdx);
          
            minOps = Math.min(minOps, 2 + (n - zeroIdx) % n);
        }

        if (isDecRotation) {
            minOps = Math.min(minOps, 1 + (n - maxIdx) % n);
            minOps = Math.min(minOps, maxIdx + 1);
        }

        return minOps;
    }
}