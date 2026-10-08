class Solution {
    public String removeOuterParentheses(String s) {
        char[] chars = s.toCharArray();
        char[] result = new char[chars.length];
        int opened = 0;
        int index = 0;

        for (char c : chars) {
            if (c == '(' && opened++ > 0) {
                result[index++] = c;
            } else if (c == ')' && --opened > 0) {
                result[index++] = c;
            }
        }

        return new String(result, 0, index);
    }
}