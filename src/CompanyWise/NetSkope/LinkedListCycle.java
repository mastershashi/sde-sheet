package CompanyWise.NetSkope;

public class LinkedListCycle {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    boolean isCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // linkedList cycle detection 2
    ListNode findMeetPoint(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // PHASE 2 once cycle is detected , move the slow pointer to head and again
                // iterator till slow != fast . this will end up in starting point of cycle
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }

        }
        return null;

    }

    public static void main(String[] args) {
        // Create nodes
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        // Create normal linked list
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // Create cycle:
        // 5 -> 3
        node5.next = node3;

        LinkedListCycle obj = new LinkedListCycle();
        System.out.println(obj.isCycle(node1));

        System.out.println("meet point " + obj.findMeetPoint(node1).val);

    }

}
