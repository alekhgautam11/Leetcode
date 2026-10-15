import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        
        // Generate all subarrays
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctElements = new HashSet<>();
            
            for (int j = i; j < n; j++) {
                // Add the current element to the set
                distinctElements.add(nums.get(j));
                
                // The size of the set is the distinct count of the subarray nums[i..j]
                int count = distinctElements.size();
                totalSum += count * count;
            }
        }
        
        return totalSum;
    }
}