import java.util.*;

public class BreadthFirstSearch {
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode(int x) {
        val = x;
        left = right = next = null;
    }

    // level order traversal using 'next' pointer
    void printLevelOrder() {
        TreeNode nextLevelRoot = this;
        while (nextLevelRoot != null) {
            TreeNode current = nextLevelRoot;
            nextLevelRoot = null;
            while (current != null) {
                System.out.print(current.val + " ");
                if (nextLevelRoot == null) {
                    if (current.left != null)
                        nextLevelRoot = current.left;
                    else if (current.right != null)
                        nextLevelRoot = current.right;
                }
                current = current.next;
            }
            System.out.println();
        }
    }
}

class LevelOrderTraversal { // easy
    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();    // 앞으로 봐야할 개수 (같은 레벨)
            List<Integer> list = new ArrayList<>(); // result의 List에 넣을 List<Integer>를 선언함
            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                list.add(now.val);
                if (now.left != null) queue.add(now.left);
                if (now.right != null) queue.add(now.right);
            }
            result.add(list);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<List<Integer>> result = LevelOrderTraversal.traverse(root);
        System.out.println("Level order traversal: " + result);
    }
}

class ReverseLevelOrderTraversal {  // easy
    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                list.add(now.val);
                if (now.left != null) queue.add(now.left);
                if (now.right != null) queue.add(now.right);
            }
            ((LinkedList<List<Integer>>) result).addFirst(list);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<List<Integer>> result = ReverseLevelOrderTraversal.traverse(root);
        System.out.println("Reverse level order traversal: " + result);
    }
}

class ZigzagTraversal { // medium
    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isOdd = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                list.add(now.val);
                if (now.left != null) queue.add(now.left);
                if (now.right != null) queue.add(now.right);
            }
            if (!isOdd) Collections.reverse(list);
            result.add(list);
            isOdd = !isOdd;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);
        List<List<Integer>> result = ZigzagTraversal.traverse(root);
        System.out.println("Zigzag traversal: " + result);
    }
}

class LevelAverage {    // easy
    public static List<Double> findLevelAverages(TreeNode root) {
        List<Double> result = new ArrayList<>();
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                sum += now.val;
                if (now.left != null) queue.add(now.left);
                if (now.right != null) queue.add(now.right);
            }
            result.add((double) sum / size);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<Double> result = LevelAverage.findLevelAverages(root);
        System.out.print("Level averages are: " + result);
    }
}

class MinimumBinaryTreeDepth {  // easy
    public static int findDepth(TreeNode root) {
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                if (now.left == null && now.right == null) return depth;
                if (now.left != null) queue.add(now.left);
                if (now.right != null) queue.add(now.right);
            }
            depth++;
        }

        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree Minimum Depth: " + MinimumBinaryTreeDepth.findDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        System.out.println("Tree Minimum Depth: " + MinimumBinaryTreeDepth.findDepth(root));
    }
}

class LevelOrderSuccessor { // easy
    public static TreeNode findSuccessor(TreeNode root, int key) {
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isResult = false;
        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            if (isResult) return now;
            if (now.val == key) isResult = true;
            if (now.left != null) queue.add(now.left);
            if (now.right != null) queue.add(now.right);
        }

        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        TreeNode result = LevelOrderSuccessor.findSuccessor(root, 12);
        if (result != null)
            System.out.println(result.val + " ");
        result = LevelOrderSuccessor.findSuccessor(root, 9);
        if (result != null)
            System.out.println(result.val + " ");
    }
}

class ConnectLevelOrderSiblings {   // medium
    public static void connect(TreeNode root) {
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                if (!queue.isEmpty() && i < size - 1) now.next = queue.peek();
                if (now.left != null) queue.add(now.left);
                if (now.right != null) queue.add(now.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        ConnectLevelOrderSiblings.connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();
    }
}

class ConnectAllSiblings {  // challenge 1
    public static void connect(TreeNode root) {
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode prev, now = null;
        while (!queue.isEmpty()) {
            prev = now;
            now = queue.poll();
            if (prev != null) prev.next = now;
            if (now.left != null) queue.add(now.left);
            if (now.right != null) queue.add(now.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        ConnectAllSiblings.connect(root);

        // level order traversal using 'next' pointer
        TreeNode current = root;
        System.out.println("Traversal using 'next' pointer: ");
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}

class RightViewTree {   // challenge 2
    public static List<TreeNode> traverse(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                if (i == size - 1) result.add(now);
                if (now.left != null) queue.add(now.left);
                if (now.right != null) queue.add(now.right);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(3);
        List<TreeNode> result = RightViewTree.traverse(root);
        for (TreeNode node : result) {
            System.out.print(node.val + " ");
        }
    }
}