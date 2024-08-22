import java.util.*;

public class BusService {

    // Node class to define a linked list node
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // Function to reverse a part of the linked list in groups of size k
    public static ListNode reverse(ListNode head, int k) {
        ListNode prev = null;      // Previous node after reversal
        ListNode curr = head;      // Current node to be processed
        ListNode next = null;      // Next node to be processed
        int count = 0;            // Count of nodes processed in the current group

        // Check if there are at least k nodes to reverse
        ListNode temp = head;
        for (int i = 0; i < k; i++) {
            if (temp == null) {
                return head; // Not enough nodes to reverse
            }
            temp = temp.next;
        }

        // Reverse k nodes
        temp = head;
        while (count < k) {
            next = temp.next;  // Store the next node
            temp.next = prev;  // Reverse the link
            prev = temp;       // Move prev to the current node
            temp = next;       // Move to the next node
            count++;
        }

        // Next node will be the head of the next segment to reverse
        if (next != null) {
            head.next = reverse(next, k); // Recursively reverse the remaining list
        }

        return prev; // Return the new head of the reversed segment
    }

    // Function to optimize the boarding process by reversing nodes in groups of size k
    public static ListNode optimizeBoarding(ListNode head, int k) {
        if (head == null || k <= 1) return head; // No need to reverse if list is empty or k <= 1
        return reverse(head, k); // Call reverse to handle the list
    }

    // Function to print the linked list
    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " "); // Print the current node's value
            temp = temp.next; // Move to the next node
        }
        System.out.println(); // Print a newline after the list
    }

    // Main method to run the code
    public static void main(String[] args) {
        // Create the linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;
        System.out.println("Original list:");
        printList(head); // Print the original list

        ListNode optimizedHead = optimizeBoarding(head, k);

        System.out.println("Optimized list with k = " + k + ":");
        printList(optimizedHead); // Print the list after optimization

        // Another example with k = 3
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        k = 3;
        System.out.println("Original list:");
        printList(head); // Print the original list

        optimizedHead = optimizeBoarding(head, k);

        System.out.println("Optimized list with k = " + k + ":");
        printList(optimizedHead); // Print the list after optimization
    }
}
