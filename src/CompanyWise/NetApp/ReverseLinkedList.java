package CompanyWise.NetApp;

public class ReverseLinkedList {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node reverse(Node head) {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        int i = 8;
        int k = 2;
        Node temp = head;
        while (i > 0) {
            Node newNode = new Node(k++);
            temp.next = newNode;
            temp = temp.next;
            i--;
        }
        System.out.println("Before reverse");
        Node temp1 = head;
        while (temp1 != null) {
            System.out.print(temp1.data + "->");
            temp1 = temp1.next;
        }

        System.out.println("\nAfter reverse");
        Node temp2 = reverse(head);
        System.out.println(temp2.data);
        while (temp2 != null) {
            System.out.print(temp2.data + "->");
            temp2 = temp2.next;
        }

    }

}
