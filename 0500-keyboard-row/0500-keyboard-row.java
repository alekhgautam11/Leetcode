import java.util.ArrayList;
import java.util.List;

class Solution {
    public String[] findWords(String[] words) {
      
        int[] rowMap = {
            2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 
            3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3
        };

        List<String> result = new ArrayList<>();

        for (String word : words) {
            boolean isValid = true;
           int targetRow = rowMap[Character.toLowerCase(word.charAt(0)) - 'a'];

            for (int i = 1; i < word.length(); i++) {
                if (rowMap[Character.toLowerCase(word.charAt(i)) - 'a'] != targetRow) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }
}