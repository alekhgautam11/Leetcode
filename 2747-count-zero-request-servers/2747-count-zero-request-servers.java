import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        
       Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
     
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] ans = new int[m];
        
     
        Map<Integer, Integer> activeServers = new HashMap<>();
        
        int left = 0; 
        int right = 0; 
       for (int i = 0; i < m; i++) {
            int queryTime = sortedQueries[i][0];
            int originalIdx = sortedQueries[i][1];
            int startTime = queryTime - x;
            
           while (right < logs.length && logs[right][1] <= queryTime) {
                int serverId = logs[right][0];
                activeServers.put(serverId, activeServers.getOrDefault(serverId, 0) + 1);
                right++;
            }
            
           while (left < logs.length && logs[left][1] < startTime) {
                int serverId = logs[left][0];
                int count = activeServers.get(serverId);
                if (count == 1) {
                    activeServers.remove(serverId);
                } else {
                    activeServers.put(serverId, count - 1);
                }
                left++;
            }
            
          
            ans[originalIdx] = n - activeServers.size();
        }
        
        return ans;
    }
}