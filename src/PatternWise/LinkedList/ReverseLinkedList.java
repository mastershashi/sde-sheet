package PatternWise.LinkedList;

public class ReverseLinkedList {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    static Node reverseLinkedList(Node head){
        Node current = head;
        Node prev= null;
        Node next = null;
        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void main(String[] args) {

        Node start = new Node(1);
        Node head = start;
        int i = 2;
        int n = 5;
        while(i <= n){
            Node temp = new Node(i);
            start.next = temp;
            start = start.next;
            i++;
        }

        System.out.println("Your LinkedList");
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
       head = ReverseLinkedList.reverseLinkedList(head);

       System.out.println("Your Reversed LinkedList");
       temp = head;
       while(temp != null){
        System.out.print(temp.data + "->");
        temp = temp.next;
    }
    }
    
}
