class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        java.util.Set<Integer> set = new java.util.HashSet<>();
        for (int x : arr) {
            set.add(x);
        }
        
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = arr[i], b = arr[j];
                int len = 2;
                while (set.contains(a + b)) {
                    int sum = a + b;
                    a = b;
                    b = sum;
                    len++;
                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        
        return maxLen;
    }
}