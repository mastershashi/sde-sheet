package CompanyWise.NetSkope;

import java.util.PriorityQueue;

public class MergeKSortedList {
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

    ListNode merge(ListNode[] list) {
        // min heap
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        // add first node of each list into priority queue
        for (ListNode head : list) {
            if (head != null) {
                pq.offer(head);
            }
        }
        ListNode temp = new ListNode();
        ListNode tail = temp;

        while (!pq.isEmpty()) {
            ListNode current = pq.poll();
            tail.next = current;
            tail = tail.next;
            if (current.next != null) {
                pq.offer(current.next);
            }

        }
        return temp.next;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(6);

        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(5);
        list2.next.next = new ListNode(7);

        ListNode list3 = new ListNode(3);
        list3.next = new ListNode(6);
        list3.next.next = new ListNode(9);
        int k = 3;

        ListNode[] list = new ListNode[k];
        list[0] = list1;
        list[1] = list2;
        list[2] = list3;

        MergeKSortedList obj = new MergeKSortedList();
        ListNode head = obj.merge(list);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }

}
