class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        int count = 0;

        int[] stack = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    long sum = 0;
                    int top = 0;
                    stack[top++] = i * n + j;
                    visited[i][j] = true;

                    while (top > 0) {
                        int cur = stack[--top];
                        int r = cur / n, c = cur % n;
                        sum += grid[r][c];

                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d], nc = c + dc[d];
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n
                                    && grid[nr][nc] > 0 && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                stack[top++] = nr * n + nc;
                            }
                        }
                    }

                    if (sum % k == 0) count++;
                }
            }
        }
        return count;
    }
}