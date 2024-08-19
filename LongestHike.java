public class LongestHike {

    public static int findLongestUphillHike(int[] elevations, int maxElevationGain) {
        int longestHike = 0; // Tracks the maximum length of an uphill hike
        int currentHikeLength = 1; // Tracks the current length of a valid uphill hike

        for (int i = 1; i < elevations.length; i++) {
            // Check if the current segment is uphill and within the allowed elevation gain limit
            if (elevations[i] > elevations[i - 1] && elevations[i] - elevations[i - 1] <= maxElevationGain) {
                currentHikeLength++; // Extend the current hike length
            } else {
                // Reset the current hike length as the segment is no longer valid
                currentHikeLength = 1;
            }
            // Update the maximum length of the hike found
            longestHike = Math.max(longestHike, currentHikeLength);
        }

        return longestHike;
    }

    public static void main(String[] args) {
        int[] elevationTrail = {4, 2, 1, 4, 3, 4, 5, 8, 15};
        int maxElevationChange = 3;

        int longestUphillHike = findLongestUphillHike(elevationTrail, maxElevationChange);
        System.out.println("The longest continuous uphill hike length is: " + longestUphillHike); // Expected output: 5
    }
}

