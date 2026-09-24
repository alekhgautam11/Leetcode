class Solution {
    public boolean isPalindrome(String s) {
        char[] charArr = s.toCharArray();
        int left = 0;
        int right = charArr.length - 1;

        while (left < right) {
            while (left < right && !isAlphanumeric(charArr[left])) {
                left++;
            }
           while (left < right && !isAlphanumeric(charArr[right])) {
                right--;
            }

            if (toLower(charArr[left]) != toLower(charArr[right])) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    private boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || 
               (c >= 'A' && c <= 'Z') || 
               (c >= '0' && c <= '9');
    }

    private char toLower(char c) {
        return (c >= 'A' && c <= 'Z') ? (char) (c + 32) : c;
    }
}