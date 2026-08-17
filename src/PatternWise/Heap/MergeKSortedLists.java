package PatternWise.Heap;

import java.util.PriorityQueue;

public class MergeKSortedLists {

    // Node of a linked list
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {

        // Min Heap
        // It will always give us the node with the smallest value.
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        // Step 1:
        // Put the first node of every list into the heap.
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }

        // Dummy node helps us easily build the result list.
        ListNode dummy = new ListNode(0);

        // tail points to the last node of our answer.
        ListNode tail = dummy;

        // Step 2:
        // Keep taking the smallest node.
        while (!pq.isEmpty()) {

            // Get the smallest node
            ListNode current = pq.poll();

            // Add it to the result
            tail.next = current;
            tail = tail.next;

            // Step 3:
            // Add the next node from the same list.
            if (current.next != null) {
                pq.offer(current.next);
            }
        }

        // dummy itself is not part of the answer.
        return dummy.next;
    }

    // Utility function to print a linked list
    public static void printList(ListNode head) {

        while (head != null) {
            System.out.print(head.val);

            if (head.next != null) {
                System.out.print(" -> ");
            }

            head = head.next;
        }

        System.out.println();
    }

    // Main method
    public static void main(String[] args) {

        /*
         * List 1: 1 -> 4 -> 5
         * List 2: 1 -> 3 -> 4
         * List 3: 2 -> 6
         */

        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);

        // Array containing all K lists
        ListNode[] lists = {
                list1,
                list2,
                list3
        };

        // Merge all lists
        ListNode result = mergeKLists(lists);

        // Print result
        System.out.println("Merged List:");
        printList(result);
    }
}
