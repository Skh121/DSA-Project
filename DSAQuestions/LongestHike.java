public class LongestHike {
    public static int longestHike(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];
        dp[0] = 1;  // The hike starts with the first element

        int maxLength = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = 1;  // Minimum length is 1 (the element itself)

            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j] && nums[i] - nums[j] <= k) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] trail = {4, 2, 1, 4, 3, 4, 5, 8, 15};
        int k = 3;
        int result = longestHike(trail, k);
        System.out.println("The longest hike length is: " + result); // Output: 5
    }
}
