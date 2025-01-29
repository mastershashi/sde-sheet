package PatternWise.LinkedList;

public class LinkedListCycleDetection {
    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    static boolean isCyclePresent(Node head){
        if (head == null || head.next == null) {
            return false;  // No cycle possible if the list is empty or has only one node
        }
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if( slow == fast){
                return true;
            }
        }
        return false;
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
        start.next = head;

        System.out.println("Your LinkedList has cycle "+ LinkedListCycleDetection.isCyclePresent(head));
        
    }
    
}
