import java.util.*;

public class DepthFirstSearch {
}

class TreePathSum { // easy
    public static boolean hasPath(TreeNode root, int sum) {
        // TODO: Write your code here
        if (root == null) return false;
        if (root.val == sum && root.left == null && root.right == null) return true;

        return hasPath(root.left, sum - root.val) || hasPath(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + TreePathSum.hasPath(root, 23));
        System.out.println("Tree has path: " + TreePathSum.hasPath(root, 16));
    }
}

class FindAllTreePaths {    // medium
    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        // TODO: Write your code here
        List<Integer> current = new ArrayList<>();
        dfs(root, sum, current, allPaths);

        return allPaths;
    }

    public static void dfs(TreeNode root, int sum, List<Integer> current, List<List<Integer>> allPaths) {
        if (root == null) return;

        current.add(root.val);

        if (root.val == sum && root.left == null && root.right == null) {
            allPaths.add(new ArrayList<>(current)); // answer
        } else {
            dfs(root.left, sum - root.val, current, allPaths);
            dfs(root.right, sum - root.val, current, allPaths);
        }
        current.remove(current.size() - 1); // backtrack remove
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = FindAllTreePaths.findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }
}

class SumOfPathNumbers {    // medium
    public static int findSumOfPathNumbers(TreeNode root) {
        // TODO: Write your code here
        List<Integer> list = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        dfs(root, current, list);

        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            res += list.get(i);
        }

        return res;
    }

    public static void dfs(TreeNode now, List<Integer> current, List<Integer> list) {
        if (now == null) return;
        if (now.left == null && now.right == null) {
            current.add(now.val);
            int value = 0;
            for (int i = 0; i < current.size(); i++) {
                value += current.get(current.size() - i - 1) * Math.pow(10, i);
            }
            list.add(value);
        } else {
            current.add(now.val);
            dfs(now.left, current, list);
            dfs(now.right, current, list);
        }
        current.remove(current.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " + SumOfPathNumbers.findSumOfPathNumbers(root));
    }
}

class PathWithGivenSequence {   // medium
    public static boolean findPath(TreeNode root, int[] sequence) {
        // TODO: Write your code here
        if (root == null) return sequence.length == 0;
        if (root.val != sequence[0]) return false;

        int[] nextSequence = new int[sequence.length - 1];
        for (int i = 1; i < sequence.length; i++) {
            nextSequence[i - 1] = sequence[i];
        }

        return findPath(root.left, nextSequence) || findPath(root.right, nextSequence);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[]{1, 0, 7}));
        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[]{1, 1, 6}));
    }
}

class CountAllPathSum { // medium
    static int cnt = 0;

    public static int countPaths(TreeNode root, int S) {
        // TODO: Write your code here
        dfs(root, S, 0);

        return cnt;
    }

    public static void dfs(TreeNode now, int S, int sum) {
        if (now == null) return;    // 재귀함수 종료조건
        sum += now.val; // sum : 지금까지의 합
        if (S == sum) cnt++;    // 합이 목표치(S)와 같을경우 count
        dfs(now.left, S, sum);  // 이어서 찾기
        dfs(now.left, S, 0);    // 새로 찾기
        dfs(now.right, S, sum); // 이어서 찾기
        dfs(now.right, S, 0);   // 새로 찾기
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + CountAllPathSum.countPaths(root, 11));
    }
}

class TreeDiameter {    // challenge 1
    public static int res = 0;

    public static int findDiameter(TreeNode root) {
        // TODO: Write your code here
        dfs(root);
        return res;
    }

    public static int dfs(TreeNode now) {
        if (now == null) return 0;
        int left = dfs(now.left);
        int right = dfs(now.right);
        if (left != 0 && right != 0) {
            res = Math.max(res, left + right + 1);
        }
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
    }
}

class MaximumPathSum {  // challenge 2
    static int res;

    public static int findMaximumPathSum(TreeNode root) {
        // TODO: Write your code here
        res = Integer.MIN_VALUE;
        dfs(root);
        return res;
    }

    public static int dfs(TreeNode now) {
        if (now == null) return 0;

        int left = Math.max(dfs(now.left), 0);
        int right = Math.max(dfs(now.right), 0);
        res = Math.max(res, left + right + now.val);
        return Math.max(left, right) + now.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));

        root = new TreeNode(-1);
        root.left = new TreeNode(-3);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));
    }
}