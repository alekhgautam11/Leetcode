class Solution {
    public String reverseWords(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        char[] result = new char[n];
        int resIdx = 0;
        
        int i = n - 1;
        while (i >= 0) {
            while (i >= 0 && str[i] == ' ') {
                i--;
            }
            if (i < 0) break;
            
            int end = i;
            while (i >= 0 && str[i] != ' ') {
                i--;
            }
            
            if (resIdx > 0) {
                result[resIdx++] = ' ';
            }
            
            for (int j = i + 1; j <= end; j++) {
                result[resIdx++] = str[j];
            }
        }
        
        return new String(result, 0, resIdx);
    }
}