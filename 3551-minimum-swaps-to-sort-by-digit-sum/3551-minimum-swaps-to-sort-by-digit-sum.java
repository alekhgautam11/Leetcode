class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[][] items = new Integer[n][2];
        
        for (int i = 0; i < n; i++) {
            items[i][0] = nums[i];
            items[i][1] = getDigitSum(nums[i]);
        }
        
        java.util.Arrays.sort(items, (a, b) -> {
            if (!a[1].equals(b[1])) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        
        java.util.Map<Integer, Integer> posMap = new java.util.HashMap<>();
        for (int i = 0; i < n; i++) {
            posMap.put(items[i][0], i);
        }
        
        boolean[] visited = new boolean[n];
        int swaps = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || posMap.get(nums[i]) == i) {
                continue;
            }
            
            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = posMap.get(nums[j]);
                cycleSize++;
            }
            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }
        
        return swaps;
    }
    
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}