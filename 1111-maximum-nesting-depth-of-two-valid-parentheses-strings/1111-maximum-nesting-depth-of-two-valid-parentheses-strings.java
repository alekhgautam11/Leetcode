class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        int depth = 0;
        char[] chars = seq.toCharArray();
        
        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                depth++;
                res[i] = depth & 1; 
            } else {
                res[i] = depth & 1; 
                depth--;
            }
        }
        
        return res;
    }
}