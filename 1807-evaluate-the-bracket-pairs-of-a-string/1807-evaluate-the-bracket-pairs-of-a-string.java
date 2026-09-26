import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>((int) (knowledge.size() / 0.75f) + 1);
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

      
        StringBuilder sb = new StringBuilder(s.length());
        int n = s.length();
        int i = 0;

        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                int start = ++i;
                while (s.charAt(i) != ')') {
                    i++;
                }
                String key = s.substring(start, i);
                sb.append(map.getOrDefault(key, "?"));
            } else {
                sb.append(c);
            }
            i++;
        }

        return sb.toString();
    }
}