import java.util.*;

class Solution {
    int[] bit;
    int n;

    void upd(int i, int v) {
        for (; i <= n + 1; i += i & -i) bit[i] ^= v;
    }

    int qry(int i) {
        int r = 0;
        for (; i > 0; i -= i & -i) r ^= bit[i];
        return r;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        bit = new int[n + 3];
        int LOG = 17;

        // adjacency list
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] nxt = new int[2 * (n - 1)];
        int[] to = new int[2 * (n - 1)];
        int cnt = 0;
        for (int[] e : edges) {
            to[cnt] = e[1]; nxt[cnt] = head[e[0]]; head[e[0]] = cnt++;
            to[cnt] = e[0]; nxt[cnt] = head[e[1]]; head[e[1]] = cnt++;
        }

        char[] c = s.toCharArray();
        int[] tin = new int[n], tout = new int[n], depth = new int[n], base = new int[n];
        int[][] up = new int[LOG][n];

        // iterative DFS
        int[] stack = new int[n];
        int[] ptr = new int[n];
        for (int i = 0; i < n; i++) ptr[i] = head[i];
        int sp = 0, timer = 0;
        stack[sp++] = 0;
        up[0][0] = 0;
        tin[0] = ++timer;
        base[0] = 1 << (c[0] - 'a');
        while (sp > 0) {
            int u = stack[sp - 1];
            if (ptr[u] != -1) {
                int e = ptr[u];
                ptr[u] = nxt[e];
                int v = to[e];
                if (u != 0 && v == up[0][u]) continue;
                up[0][v] = u;
                depth[v] = depth[u] + 1;
                base[v] = base[u] ^ (1 << (c[v] - 'a'));
                tin[v] = ++timer;
                stack[sp++] = v;
            } else {
                tout[u] = timer;
                sp--;
            }
        }
        for (int k = 1; k < LOG; k++)
            for (int i = 0; i < n; i++)
                up[k][i] = up[k - 1][up[k - 1][i]];

        List<Boolean> res = new ArrayList<>();
        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char nc = p[2].charAt(0);
                if (nc != c[u]) {
                    int d = (1 << (c[u] - 'a')) ^ (1 << (nc - 'a'));
                    c[u] = nc;
                    upd(tin[u], d);
                    upd(tout[u] + 1, d);
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int a = u, b = v;
                if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
                int diff = depth[a] - depth[b];
                for (int k = 0; k < LOG; k++)
                    if ((diff >> k & 1) == 1) a = up[k][a];
                int l;
                if (a == b) l = a;
                else {
                    for (int k = LOG - 1; k >= 0; k--) {
                        if (up[k][a] != up[k][b]) {
                            a = up[k][a];
                            b = up[k][b];
                        }
                    }
                    l = up[0][a];
                }
                int pu = base[u] ^ qry(tin[u]);
                int pv = base[v] ^ qry(tin[v]);
                int mask = pu ^ pv ^ (1 << (c[l] - 'a'));
                res.add(Integer.bitCount(mask) <= 1);
            }
        }
        return res;
    }
}