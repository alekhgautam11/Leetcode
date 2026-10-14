class Solution {
    public String maxSumOfSquares(int num, int sum) {
        char[] res = new char[num];
        int currentSum = sum;
        
        for (int i = 0; i < num; i++) {
            int cur = Math.min(9, currentSum);
            res[i] = (char) ('0' + cur);
            currentSum -= cur;
        }
        
    
        if (currentSum > 0) {
            return "";
        }
        
        return new String(res);
    }
}