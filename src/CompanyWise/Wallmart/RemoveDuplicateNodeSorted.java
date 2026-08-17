package CompanyWise.Wallmart;

public class RemoveDuplicateNodeSorted {
    public static class ListNode {
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

    public ListNode deleteDuplicates(ListNode head) {
        ListNode firstNode = head;
        ListNode secondNode = head.next;
        while (head.next != null) {
            if (firstNode.val == secondNode.val) {
                firstNode.next = firstNode.next.next;
                break;
            }
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }
        return head;

    }

    public static void main(String[] args) {
        ListNode node = new ListNode();
        node.val = 5;
        ListNode node1 = new ListNode();
        node1.val = 6;
        ListNode node2 = new ListNode();
        node2.val = 6;
        ListNode node3 = new ListNode();
        node3.val = 7;
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        System.out.println("Before");
        ListNode temp = node;
        while (temp != null) {
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println();
        RemoveDuplicateNodeSorted linkedList = new RemoveDuplicateNodeSorted();
        ListNode head = linkedList.deleteDuplicates(node);
        System.out.println("After");
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();

    }

}
