class Solution {
    private int rows, cols;

    public int closedIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 0) dfs(grid, i, 0);
            if (grid[i][cols - 1] == 0) dfs(grid, i, cols - 1);
        }
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 0) dfs(grid, 0, j);
            if (grid[rows - 1][j] == 0) dfs(grid, rows - 1, j);
        }

        int count = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0) {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] != 0) return;
        grid[r][c] = 1; 
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}