import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
      
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
          pq.offer(new int[]{0, 0, 1});
        dist[0][1] = 0;
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int w = curr[0];
            int u = curr[1];
            int count = curr[2];
            
           if (u == n - 1) {
                return w;
            }
            
            if (w > dist[u][count]) {
                continue;
            }
            
            char uChar = labels.charAt(u);
            
            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int edgeWeight = neighbor[1];
                char vChar = labels.charAt(v);
                
                int nextCount;
                if (uChar == vChar) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }
                
                if (nextCount <= k) {
                    int nextWeight = w + edgeWeight;
                    if (nextWeight < dist[v][nextCount]) {
                        dist[v][nextCount] = nextWeight;
                        pq.offer(new int[]{nextWeight, v, nextCount});
                    }
                }
            }
        }
        
       int ans = Integer.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            ans = Math.min(ans, dist[n - 1][c]);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}