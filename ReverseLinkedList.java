import java.util.*;

class ReverseLinkedList {    // easy
    public static ListNode reverse(ListNode head) {
        // TODO: Write your code here
        ListNode currentNode = head;
        ListNode previousNode = null;
        ListNode temp;

        while (currentNode != null) {
            temp = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = temp;
        }

        return previousNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);

        ListNode result = ReverseLinkedList.reverse(head);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}

class ReverseSubList {  // medium
    public static ListNode reverse(ListNode head, int p, int q) {
        // TODO: Write your code here
        ListNode current = head;
        ListNode prev = null, next;

        for (int i = 0; i < p - 1 && current.next != null; i++) {
            prev = current;
            current = current.next;
        }   // p에 도달
        ListNode lastNode = prev;   // 1    // 1.next = 4를 만들기 위함
        ListNode subNode = current; // 2    // 2.next = 5를 만들기 위함

        for (int i = p; i < q + 1; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }   // reverse

        if (lastNode != null) lastNode.next = prev;
        else head = prev;
        subNode.next = current;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode result = ReverseSubList.reverse(head, 2, 4);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}

//class ReverseEveryKElements { // medium
//    public static ListNode reverse(ListNode head, int k) {
//        // TODO: Write your code here
//        ListNode current = head;
//        ListNode prev = null, next, last, sub;
//
//        while (true) {
//            last = prev;
//            sub = current;
//            for (int i = 0; i < k && current != null; i++) {
//                next = current.next;
//                current.next = prev;
//                prev = current;
//                current = next;
//            }   // reverse
//
//            if (last != null) last.next = prev;
//            else head = prev;
//            sub.next = current;
//
//            if (current == null) break;
//            prev = sub;
//        }
//
//        return head;
//    }
//
//    public static void main(String[] args) {
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
//        head.next.next.next.next.next.next = new ListNode(7);
//        head.next.next.next.next.next.next.next = new ListNode(8);
//
//        ListNode result = ReverseEveryKElements.reverse(head, 3);
//        System.out.print("Nodes of the reversed LinkedList are: ");
//        while (result != null) {
//            System.out.print(result.value + " ");
//            result = result.next;
//        }
//    }
//}

class ReverseEveryKElements {
    public static ListNode reverse(ListNode head, int k) {
        // TODO: Write your code here
        ListNode current = head;
        ListNode prev = null, next, last, sub;

        while (true) {
            last = prev;
            sub = current;
            for (int i = 0; i < k && current != null; i++) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }   // reverse
            if (last != null) last.next = prev;
            else head = prev;
            sub.next = current;

            for (int i = 0; i < k && current != null; i++) {
                prev = current;
                current = current.next;
            }   // onGoing

            if (current == null) break;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode result = ReverseEveryKElements.reverse(head, 2);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}