
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        Arrays.sort(nums);

        long sum = 0;
        int n = nums.length;

        // Sort by absolute value using a separate array of squares
        long[] squares = new long[n];

        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                sum -= squares[i];
            } else {
                sum += squares[i];
            }
        }

        return sum;
    }
}
