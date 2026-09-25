import java.util.*;

public class Solution {
    private int idx = 0;

    public List<String> braceExpansionII(String expression) {
        idx = 0;
        Set<String> set = parseExpression(expression);
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    private Set<String> parseExpression(String expr) {
        Set<String> res = parseTerm(expr);
        while (idx < expr.length() && expr.charAt(idx) == ',') {
            idx++; // consume ','
            res.addAll(parseTerm(expr));
        }
        return res;
    }

   
    private Set<String> parseTerm(String expr) {
        Set<String> res = parseFactor(expr);
        while (idx < expr.length() && (Character.isLowerCase(expr.charAt(idx)) || expr.charAt(idx) == '{')) {
            Set<String> next = parseFactor(expr);
            res = multiply(res, next);
        }
        return res;
    }

    private Set<String> parseFactor(String expr) {
        Set<String> res = new HashSet<>();
        if (idx < expr.length() && Character.isLowerCase(expr.charAt(idx))) {
            res.add(String.valueOf(expr.charAt(idx)));
            idx++;
        } else if (idx < expr.length() && expr.charAt(idx) == '{') {
            idx++; 
            res = parseExpression(expr);
            idx++; 
        }
        return res;
    }

    private Set<String> multiply(Set<String> s1, Set<String> s2) {
        Set<String> res = new HashSet<>();
        for (String a : s1) {
            for (String b : s2) {
                res.add(a + b);
            }
        }
        return res;
    }
}