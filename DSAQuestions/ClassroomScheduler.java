import java.util.*;
 
public class ClassroomScheduler {
 
    static class Event {
        int time;
        int type; // 1 for start, -1 for end
        int students;
        int roomIndex;
 
        public Event(int time, int type, int students, int roomIndex) {
            this.time = time;
            this.type = type;
            this.students = students;
            this.roomIndex = roomIndex;
        }
    }
 
    public static int findBusiestClassroom(int numRooms, int[][] sessions) {
        List<Event> events = new ArrayList<>();
 
        // Convert each class session into start and end events
        for (int i = 0; i < sessions.length; i++) {
            int start = sessions[i][0];
            int end = sessions[i][1];
            int students = sessions[i][2];
 
            // Start event
            events.add(new Event(start, 1, students, i));
            // End event
            events.add(new Event(end, -1, students, i));
        }
 
        // Sort events: primary by time, secondary by type (end events before start events if same time)
        events.sort((a, b) -> {
            if (a.time == b.time) {
                return a.type - b.type;
            }
            return a.time - b.time;
        });
 
        // Track the number of classes and the current number of students in each room
        int[] roomClasses = new int[numRooms];
        int[] roomStudentCount = new int[numRooms];
        int maxClasses = 0;
        int busiestRoom = -1;
 
        // Process all events
        for (Event event : events) {
            if (event.type == 1) { // Start event
                int roomIndex = -1;
                int minStudentCount = Integer.MAX_VALUE;
 
                // Find the room with the minimum number of students
                for (int i = 0; i < numRooms; i++) {
                    if (roomStudentCount[i] < minStudentCount) {
                        minStudentCount = roomStudentCount[i];
                        roomIndex = i;
                    }
                }
 
                // Assign the room to the class
                roomClasses[roomIndex]++;
                roomStudentCount[roomIndex] += event.students;
 
                // Update max classes if needed
                if (roomClasses[roomIndex] > maxClasses) {
                    maxClasses = roomClasses[roomIndex];
                    busiestRoom = roomIndex;
                }
            } else { // End event
                // Find which room this class was assigned to
                for (int i = 0; i < numRooms; i++) {
                    if (roomClasses[i] > 0) {
                        roomClasses[i]--;
                        roomStudentCount[i] -= event.students;
                        break;
                    }
                }
            }
        }
 
        return busiestRoom;
    }
 
    public static void main(String[] args) {
        int numRooms1 = 2;
        int[][] sessions1 = {{0, 10, 30}, {1, 5, 20}, {2, 7, 25}, {3, 4, 10}};
        System.out.println(findBusiestClassroom(numRooms1, sessions1)); // Output: 0
 
        int numRooms2 = 3;
        int[][] sessions2 = {{1, 20, 50}, {2, 10, 40}, {3, 5, 30}, {4, 9, 25}, {6, 8, 20}};
        System.out.println(findBusiestClassroom(numRooms2, sessions2)); // Output: 1
    }
}
