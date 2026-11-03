class Solution {
    public int minMoves(int[] nums) {
        int mx = 0, sum = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
            sum += x;
        }
        return mx * nums.length - sum;
    }
}