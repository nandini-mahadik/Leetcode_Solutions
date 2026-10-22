class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int k = nums[0];
        int best = Integer.MAX_VALUE;

        // Case A: rotation of ascending
        boolean asc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (i + k) % n) { asc = false; break; }
        }
        if (asc) {
            if (k == 0) best = 0;
            else best = Math.min(n - k, k + 2);
        }

        // Case B: rotation of descending
        boolean desc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (((k - i) % n) + n) % n) { desc = false; break; }
        }
        if (desc) {
            int t = (k + 1) % n;
            int cost = Math.min(t, n - t) + 1;
            best = Math.min(best, cost);
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}