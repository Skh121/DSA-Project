import java.util.HashMap;

public class MovieTheaterSeating {
    // Determine if any two friends can sit together based on the given index and value difference constraints
    public boolean canFriendsSitTogether(int[] seats, int maxIndexDiff, int maxValueDiff) {
        if (seats == null || seats.length < 2) {
            return false; // Not enough seats to have two friends
        }

        HashMap<Integer, Integer> seatMap = new HashMap<>();

        for (int i = 0; i < seats.length; i++) {
            // Check if there is a valid pair within the constraints
            for (int key : seatMap.keySet()) {
                if (Math.abs(seats[i] - key) <= maxValueDiff && Math.abs(i - seatMap.get(key)) <= maxIndexDiff) {
                    return true; // Found a valid pair
                }
            }

            // Add the current seat and its index to the map
            seatMap.put(seats[i], i);

            // Maintain the sliding window of size maxIndexDiff
            if (i >= maxIndexDiff) {
                seatMap.remove(seats[i - maxIndexDiff]);
            }
        }

        return false; // No valid pair found
    }

    public static void main(String[] args) {
        MovieTheaterSeating theaterSeating = new MovieTheaterSeating();

        int[] seats = {1, 2, 4, 6, 7}; // Example seat values
        int maxIndexDiff = 2; // Maximum allowed index difference
        int maxValueDiff = 1; // Maximum allowed value difference
        
        // Check if any two friends can sit together based on the given constraints
        System.out.println(theaterSeating.canFriendsSitTogether(seats, maxIndexDiff, maxValueDiff)); // Output: true
    }
}

