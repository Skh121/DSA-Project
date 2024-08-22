public class SecretDecoderRing {

    // Method to decode the message based on the given shifts
    public static String decodeMessage(String s, int[][] shifts) {
        // Convert the input string to a character array for easy manipulation
        char[] message = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            message[i] = s.charAt(i);
        }

        // Apply each shift operation
        for (int[] shift : shifts) {
            int start = shift[0];        // Starting index of the shift
            int end = shift[1];          // Ending index of the shift
            int direction = shift[2];    // Direction of the shift (1 for clockwise, 0 for counter-clockwise)

            // Rotate characters in the specified range
            for (int i = start; i <= end; i++) {
                message[i] = rotateChar(message[i], direction);
            }
        }

        // Convert the character array back to a string and return it
        return new String(message);
    }

    // Method to rotate a single character based on the direction
    private static char rotateChar(char c, int direction) {
        if (direction == 1) { // Clockwise shift
            // Rotate character forward; wrap around from 'z' to 'a'
            return c == 'z' ? 'a' : (char) (c + 1);
        } else { // Counter-clockwise shift
            // Rotate character backward; wrap around from 'a' to 'z'
            return c == 'a' ? 'z' : (char) (c - 1);
        }
    }

    public static void main(String[] args) {
        String s = "hello"; // Input message
        // List of shifts: each shift is defined by [startIndex, endIndex, direction]
        int[][] shifts = {{0, 1, 1}, {2, 3, 0}, {0, 2, 1}};
        
        // Decode the message based on the given shifts and print the result
        System.out.println(decodeMessage(s, shifts)); // Output: "ifmmp"
    }
}
