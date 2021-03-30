import java.util.*;

public class FastNSlowPointers {
}

class ListNode {    // init
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

class LinkedListCycle { // easy
    public static boolean hasCycle(ListNode head) {
        // TODO: Write your code here
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));
    }
}

class LinkedListCycleStart {    // medium
    public static ListNode findCycleStart(ListNode head) {
        // TODO: Write your code here
        ListNode node = head;
        ListNode nextNode = node.next;
        while (node.next != null) {
            if (nextNode.value < node.value) {  // find it
                return nextNode;
            } else {
                node = node.next;
                nextNode = node.next;
            }
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

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);
    }
}

class HappyNumber { // medium
    public static boolean find(int num) {
        List<String> list = new ArrayList<>();
        String number = Integer.toString(num);
        while (!number.equals("1")) {
            int value = 0;
            int intNum = Integer.parseInt(number);
            while (intNum > 0) {
                value += (intNum % 10) * (intNum % 10);
                intNum /= 10;
            }
            number = Integer.toString(value);
            if (list.contains(number)) return false;
            else list.add(number);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(HappyNumber.find(23));
        System.out.println(HappyNumber.find(12));
    }
}

class MiddleOfLinkedList {  // easy
    public static ListNode findMiddle(ListNode head) {
        // TODO: Write your code here
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node.next != null) {
            list.add(node);
            node = node.next;
        }
        int len = list.size();
        return list.get(len / 2 + len % 2);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);

        head.next.next.next.next.next = new ListNode(6);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);

        head.next.next.next.next.next.next = new ListNode(7);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);
    }
}

class PalindromicLinkedList {   // challenge 1
    public static boolean isPalindrome(ListNode head) {
        // TODO: Write your code here
        Stack<Integer> stack = new Stack<>();
        ListNode node = head;
        int last = node.value;
        stack.add(node.value);
        while (node.next != null) {
            node = node.next;
            if (last == node.value) {   // palindrome start
                stack.pop();
                stack.pop();
                while (node.next != null) {
                    node = node.next;
                    if (!stack.isEmpty() && stack.peek() == node.value) {
                        stack.pop();
                    } else return false;
                }
            } else {
                last = stack.peek();
                stack.add(node.value);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));

        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));
    }
}

class RearrangeList {   // challenge 2
    public static void reorder(ListNode head) {
        // TODO: Write your code here
        LinkedList<ListNode> list = new LinkedList<>();
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            list.add(node);
        }
        boolean even = true;
        node = head;
        while (!list.isEmpty()) {
            if (even) {
                node.next = list.pollLast();
                node = node.next;
                even = false;
            } else {
                node.next = list.pollFirst();
                node = node.next;
                even = true;
            }
        }
        node.next = null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        RearrangeList.reorder(head);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }
}

class CircularArrayLoop {   // challenge 3
    public static boolean loopExists(int[] arr) {
        // TODO: Write your code here
        for (int i = 0; i < arr.length; i++) {
            boolean[] visited = new boolean[arr.length];
            visited[i] = true;
            int idx = i;
            int direct = arr[idx];
            while (true) {
                if (direct * arr[idx] < 0) break;    // different direct
                idx += arr[idx];    // move
                idx %= arr.length;
                if (visited[idx]) {
                    if (idx == i) return true;
                    else break;
                } else {
                    visited[idx] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(CircularArrayLoop.loopExists(new int[]{1, 2, -1, 2, 2}));
        System.out.println(CircularArrayLoop.loopExists(new int[]{2, 2, -1, 2}));
        System.out.println(CircularArrayLoop.loopExists(new int[]{2, 1, -1, -2}));
    }
}