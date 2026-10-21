class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        
        int totalCount = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        
        for (int i = 2; i <= n; i++) {
            uniqueDigits *= availableNumber;
            totalCount += uniqueDigits;
            availableNumber--;
        }
        
        return totalCount;
    }
}