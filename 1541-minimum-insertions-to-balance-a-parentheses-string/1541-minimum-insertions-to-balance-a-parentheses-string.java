class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openCount = 0;
        char[] chars = s.toCharArray();
        int n = chars.length;

        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                openCount++;
            } else {
                if (i + 1 < n && chars[i + 1] == ')') {
                    i++;
                } else {
                    insertions++;
                }

                if (openCount > 0) {
                    openCount--;
                } else {
                    insertions++; 
                }
            }
        }

        return insertions + (openCount * 2);
    }
}