package CompanyWise.NetApp;

public class ReverseKGroupLinkedList {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    static Node recursiveReverseKGroup(Node head , int k){
        int count = 0;
        Node current = head;
        while(current!= null && count < k){
            current = current.next;
            count++;
        }
        if(count < k) 
            return head;
        
        Node prev = recursiveReverseKGroup(current,k);

        current = head;
        while(count > 0){
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count--;
        }
        return prev;

    }
    static Node reverseKgroup(Node head, int k) {
        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node previousGroupEnd = dummyNode;

        while (true) {
            Node kthNode = previousGroupEnd;
            for (int i = 0; i < k && kthNode != null; i++) {
                kthNode = kthNode.next;
            }
            if (kthNode == null)
                break;
            Node nextGroupStart = kthNode.next;
            Node curr = previousGroupEnd.next;
            Node prev = nextGroupStart;
            while (curr != nextGroupStart) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            Node temp = previousGroupEnd.next;
            previousGroupEnd.next = kthNode;
            previousGroupEnd = temp;
        }
        return dummyNode;

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        int k = 2;
        int i = 8;
        int count = 2;
        Node temp = head;
        while (i > 0) {
            Node newNode = new Node(count++);
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
        Node temp2 = recursiveReverseKGroup(head, k);
        System.out.println(temp2.data);
        while (temp2 != null) {
            System.out.print(temp2.data + "->");
            temp2 = temp2.next;
        }
    }
}
