import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list using arrays (fast, low memory)
        int m = edges.length;
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] next = new int[m];
        int[] to = new int[m];
        int[] wt = new int[m];
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            to[i] = edges[i][1];
            wt[i] = edges[i][2];
            next[i] = head[u];
            head[u] = i;
        }

        char[] lab = labels.toCharArray();

        // dist[node * k + (run - 1)]
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n * k];
        Arrays.fill(dist, INF);

        // PQ entries: {distance, stateIndex}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        dist[0] = 0; // node 0, run = 1
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int d = (int) cur[0];
            int state = (int) cur[1];
            if (d > dist[state]) continue;

            int u = state / k;
            int run = state % k + 1;

            if (u == n - 1) return d; // first time popping target is optimal

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                int newRun;
                if (lab[v] == lab[u]) {
                    newRun = run + 1;
                    if (newRun > k) continue;
                } else {
                    newRun = 1;
                }
                int ns = v * k + (newRun - 1);
                int nd = d + wt[e];
                if (nd < dist[ns]) {
                    dist[ns] = nd;
                    pq.offer(new long[]{nd, ns});
                }
            }
        }
        return -1;
    }
}