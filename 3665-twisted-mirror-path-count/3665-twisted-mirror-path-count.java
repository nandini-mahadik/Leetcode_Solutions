class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;
        int[][] R = new int[m][n];
        int[][] D = new int[m][n];
        R[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int t = (R[i][j] + D[i][j]) % MOD;
                    if (t == 0) continue;
                    if (j + 1 < n) R[i][j + 1] = (R[i][j + 1] + t) % MOD;
                    if (i + 1 < m) D[i + 1][j] = (D[i + 1][j] + t) % MOD;
                } else {
                    if (i + 1 < m) D[i + 1][j] = (D[i + 1][j] + R[i][j]) % MOD;
                    if (j + 1 < n) R[i][j + 1] = (R[i][j + 1] + D[i][j]) % MOD;
                }
            }
        }
        return (R[m - 1][n - 1] + D[m - 1][n - 1]) % MOD;
    }
}