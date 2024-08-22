import java.util.ArrayList;
import java.util.List;

public class LongestHike {
    public static int longestHike(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];       // Stores the length of the longest hike ending at each element
        int[] prev = new int[n];     // Stores the previous index in the optimal sequence

        dp[0] = 1;                   // The hike starts with the first element
        prev[0] = -1;                // No previous element for the first item

        int maxLength = 1;
        int maxIndex = 0;            // Index of the last element in the longest hike

        for (int i = 1; i < n; i++) {
            dp[i] = 1;               // Minimum length is 1 (the element itself)
            prev[i] = -1;            // Initialize previous index as -1 (no valid previous element)

            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j] && nums[i] - nums[j] <= k) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;  // Track the index of the previous element
                    }
                }
            }

            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;        // Update the index of the last element in the longest hike
            }
        }

        // Reconstruct the longest hike path
        List<Integer> path = new ArrayList<>();
        for (int i = maxIndex; i != -1; i = prev[i]) {
            path.add(0, nums[i]);    // Add the elements in reverse order
        }

        System.out.println("The longest hike path is: " + path);
        return maxLength;
    }

    public static void main(String[] args) {
        int[] trail = {4, 2, 1, 4, 3, 4, 5, 8, 15};
        int k = 3;
        int result = longestHike(trail, k);
        System.out.println("The longest hike length is: " + result); // Output: 5
    }
}
