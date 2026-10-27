class Solution {
    public String[] createGrid(int m, int n) {
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append((i == 0 || j == n - 1) ? '.' : '#');
            }
            result[i] = sb.toString();
        }
        return result;
    }
}