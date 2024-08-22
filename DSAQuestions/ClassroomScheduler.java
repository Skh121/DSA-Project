import java.util.*;

public class ClassroomScheduler {

    public static int mostUtilizedClassroom(int n, int[][] classes) {
        // Sort classes by start time, then by size (larger first) if start times are equal
        Arrays.sort(classes, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(b[1], a[1]);
            }
        });

        // Priority queue to track when each classroom will be free
        PriorityQueue<int[]> freeRooms = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // Array to track the number of classes each room has held
        int[] classCount = new int[n];

        for (int[] cls : classes) {
            int start = cls[0];
            int end = cls[1];

            // Free up rooms that have been released before the current class start time
            while (!freeRooms.isEmpty() && freeRooms.peek()[0] <= start) {
                int[] room = freeRooms.poll();
                freeRooms.add(new int[]{start, room[1]});
                break;
            }

            if (freeRooms.size() < n) {
                // There is at least one available room
                int room = freeRooms.size();
                freeRooms.add(new int[]{end, room});
                classCount[room]++;
            } else {
                // All rooms are occupied, need to delay the class
                int[] earliestEndRoom = freeRooms.poll();
                int room = earliestEndRoom[1];
                start = earliestEndRoom[0];
                end = start + (cls[1] - cls[0]);
                freeRooms.add(new int[]{end, room});
                classCount[room]++;
            }
        }

        // Find the classroom with the maximum number of classes
        int maxClasses = -1;
        int maxClassroom = -1;
        for (int i = 0; i < n; i++) {
            if (classCount[i] > maxClasses) {
                maxClasses = classCount[i];
                maxClassroom = i;
            }
        }

        return maxClassroom;
    }

    public static void main(String[] args) {
        int n1 = 2;
        int[][] classes1 = {{0, 10}, {1, 5}, {2, 7}, {3, 4}};
        System.out.println(mostUtilizedClassroom(n1, classes1));  // Output: 0

        int n2 = 3;
        int[][] classes2 = {{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}};
        System.out.println(mostUtilizedClassroom(n2, classes2));  // Output: 1
    }
}
