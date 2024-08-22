import java.util.Arrays;
import java.util.Random;

public class HillClimbingTSP {
    
    // Method to calculate the total distance of a tour
    public static int calculateDistance(int[] tour, int[][] distanceMatrix) {
        int totalDistance = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            totalDistance += distanceMatrix[tour[i]][tour[i + 1]];
        }
        totalDistance += distanceMatrix[tour[tour.length - 1]][tour[0]]; // Return to the start
        return totalDistance;
    }

    // Method to swap two cities in the tour
    public static int[] swapTwoCities(int[] tour) {
        int[] newTour = tour.clone();
        Random random = new Random();
        int i = random.nextInt(tour.length);
        int j = random.nextInt(tour.length);
        // Swap the cities
        int temp = newTour[i];
        newTour[i] = newTour[j];
        newTour[j] = temp;
        return newTour;
    }

    // Hill Climbing algorithm for TSP
    public static int[] hillClimbing(int[][] distanceMatrix) {
        int numCities = distanceMatrix.length;
        Random random = new Random();
        
        // Generate an initial tour
        int[] currentTour = new int[numCities];
        for (int i = 0; i < numCities; i++) {
            currentTour[i] = i;
        }
        for (int i = 0; i < numCities; i++) {
            int j = random.nextInt(numCities);
            int temp = currentTour[i];
            currentTour[i] = currentTour[j];
            currentTour[j] = temp;
        }
        int currentDistance = calculateDistance(currentTour, distanceMatrix);
        
        boolean improvement = true;
        while (improvement) {
            improvement = false;
            for (int i = 0; i < numCities; i++) {
                int[] neighborTour = swapTwoCities(currentTour);
                int neighborDistance = calculateDistance(neighborTour, distanceMatrix);
                
                if (neighborDistance < currentDistance) {
                    currentTour = neighborTour;
                    currentDistance = neighborDistance;
                    improvement = true;
                }
            }
        }
        
        return currentTour;
    }

    public static void main(String[] args) {
        // Example distance matrix
        int[][] distanceMatrix = {
            {0, 2, 9, 10},
            {1, 0, 6, 4},
            {15, 7, 0, 8},
            {6, 3, 12, 0}
        };
        
        int[] bestTour = hillClimbing(distanceMatrix);
        int bestDistance = calculateDistance(bestTour, distanceMatrix);
        
        System.out.println("Best tour: " + Arrays.toString(bestTour));
        System.out.println("Best distance: " + bestDistance);
    }
}

