import java.util.TreeMap;

class Solution {
    private TreeMap<Integer, Integer> left = new TreeMap<>();
    private TreeMap<Integer, Integer> right = new TreeMap<>();
    private int leftCount = 0;
    private int rightCount = 0;
    private long leftSum = 0;
    private long rightSum = 0;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n];

      
        for (int i = 0; i < n; i++) {
            add(nums[i]);
            if (i >= x) {
                remove(nums[i - x]);
            }
            balance();

            if (i >= x - 1) {
                long median = left.lastKey();
                long currentCost = (median * leftCount - leftSum) + (rightSum - median * rightCount);
                cost[i] = currentCost;
            }
        }

        long[][] dp = new long[n + 1][k + 1];
        long INF = (long) 1e18;

        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = INF;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // Option 1: Do not pick a window ending at i - 1
                dp[i][j] = dp[i - 1][j];

                // Option 2: Pick a window ending at i - 1 (starts at i - x)
                if (i >= x) {
                    if (dp[i - x][j - 1] != INF) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - x][j - 1] + cost[i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }

    private void add(int val) {
        if (left.isEmpty() || val <= left.lastKey()) {
            left.put(val, left.getOrDefault(val, 0) + 1);
            leftSum += val;
            leftCount++;
        } else {
            right.put(val, right.getOrDefault(val, 0) + 1);
            rightSum += val;
            rightCount++;
        }
    }

    private void remove(int val) {
        if (left.containsKey(val)) {
            int cnt = left.get(val);
            if (cnt == 1) left.remove(val);
            else left.put(val, cnt - 1);
            leftSum -= val;
            leftCount--;
        } else {
            int cnt = right.get(val);
            if (cnt == 1) right.remove(val);
            else right.put(val, cnt - 1);
            rightSum -= val;
            rightCount--;
        }
    }

    private void balance() {
        int targetLeft = (leftCount + rightCount + 1) / 2;

        while (leftCount > targetLeft) {
            int maxLeft = left.lastKey();
            remove(maxLeft);
            right.put(maxLeft, right.getOrDefault(maxLeft, 0) + 1);
            rightSum += maxLeft;
            rightCount++;
        }

        while (leftCount < targetLeft) {
            int minRight = right.firstKey();
            int cnt = right.get(minRight);
            if (cnt == 1) right.remove(minRight);
            else right.put(minRight, cnt - 1);
            rightSum -= minRight;
            rightCount--;

            left.put(minRight, left.getOrDefault(minRight, 0) + 1);
            leftSum += minRight;
            leftCount++;
        }
    }
}