class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);

        int l = 0;
        long sum = 0;
        int maxF = 1;

        for (int r=0; r < nums.length; r++){
            sum += nums[r];
            long windowSize = r - l + 1;

            long requiredOperations = (long) nums[r] * windowSize - sum;

            while (requiredOperations > k) {
                sum -= nums[l];
                l++;

                windowSize = r - l + 1;

                requiredOperations = (long) nums[r] * windowSize - sum;
            }

            maxF = Math.max(maxF, r - l + 1);
        }
        return maxF;
    }
}