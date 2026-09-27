import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int open = stack.pop();
                pair[open] = i;
                pair[i] = open;
            }
        }

        StringBuilder sb = new StringBuilder();
        int direction = 1; 
        for (int i = 0; i < n; i += direction) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                i = pair[i];         
                direction = -direction; 
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}