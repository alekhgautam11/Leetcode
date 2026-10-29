
import java.util.*;

class Solution {

    int[][] up;
    int[] depth, tin, tout, parent, bit, mask;
    int timer, LOG;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {

        List<Boolean> ans = new ArrayList<>();

        int[] head = new int[n];
        Arrays.fill(head, -1);

        int[] to = new int[2 * (n - 1)];
        int[] next = new int[2 * (n - 1)];
        int idx = 0;

        for (int[] e : edges) {
            to[idx] = e[1];
            next[idx] = head[e[0]];
            head[e[0]] = idx++;

            to[idx] = e[0];
            next[idx] = head[e[1]];
            head[e[1]] = idx++;
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        parent = new int[n];
        mask = new int[n];
        Arrays.fill(parent, -2);

        char[] ch = s.toCharArray();
        int[] stack = new int[n];
        int[] order = new int[n];
        int top = 0, count = 0;

        stack[top++] = 0;
        parent[0] = -1;
        mask[0] = 1 << (ch[0] - 'a');

        while (top > 0) {
            int u = stack[--top];
            tin[u] = count;
            order[count++] = u;

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];

                if (parent[v] != -2) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                mask[v] = mask[u] ^ (1 << (ch[v] - 'a'));
                stack[top++] = v;
            }
        }

        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--)
            size[parent[order[i]]] += size[order[i]];

        for (int u = 0; u < n; u++)
            tout[u] = tin[u] + size[u] - 1;

        for (int u = 0; u < n; u++)
            up[0][u] = Math.max(0, parent[u]);

        for (int k = 1; k < LOG; k++)
            for (int u = 0; u < n; u++)
                up[k][u] = up[k - 1][up[k - 1][u]];

        bit = new int[n + 2];

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                int delta = (1 << (ch[u] - 'a')) ^ (1 << (c - 'a'));

                if (delta != 0) {
                    update(tin[u] + 1, delta);
                    update(tout[u] + 2, delta);
                    ch[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int lca = getLCA(u, v);

                int m = getMask(u) ^ getMask(v)
                      ^ (1 << (ch[lca] - 'a'));

                ans.add((m & (m - 1)) == 0);
            }
        }

        return ans;
    }

    int getMask(int u) {
        return mask[u] ^ query(tin[u] + 1);
    }

    void update(int i, int val) {
        while (i < bit.length) {
            bit[i] ^= val;
            i += i & -i;
        }
    }

    int query(int i) {
        int res = 0;
        while (i > 0) {
            res ^= bit[i];
            i -= i & -i;
        }
        return res;
    }

    int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int d = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++)
            if ((d & (1 << k)) != 0)
                u = up[k][u];

        if (u == v) return u;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }
}
