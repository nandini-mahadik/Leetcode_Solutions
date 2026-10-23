class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1; 
        boolean sorted = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                ans &= nums[i];
                sorted = false;
            }
        }
        return sorted ? 0 : ans;
    }
}