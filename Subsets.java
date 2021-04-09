import java.util.*;

class Subsets { // easy
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        for (int num : nums) {
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(num);
                subsets.add(set);
            }
        }

        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Subsets.findSubsets(new int[]{1, 3});
        System.out.println("Here is the list of subsets: " + result);

        result = Subsets.findSubsets(new int[]{1, 5, 3});
        System.out.println("Here is the list of subsets: " + result);
    }
}

class SubsetWithDuplicates {    // easy
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        subsets.add(new ArrayList<>());
        int start = 0, end = 0;
        for (int i = 0; i < nums.length; i++) {
            start = 0;
            if (i > 0 && nums[i] == nums[i - 1])
                start = end + 1;
            end = subsets.size() - 1;
            for (int j = start; j <= end; j++) {
                List<Integer> set = new ArrayList<>(subsets.get(j));
                set.add(nums[i]);
                subsets.add(set);
            }
        }

        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = SubsetWithDuplicates.findSubsets(new int[]{1, 3, 3});
        System.out.println("Here is the list of subsets: " + result);

        result = SubsetWithDuplicates.findSubsets(new int[]{1, 5, 3, 3});
        System.out.println("Here is the list of subsets: " + result);
    }
}

class Permutations {    // medium
    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> perm = new LinkedList<>();
        perm.add(new ArrayList<>());
        for (int num : nums) {
            int n = perm.size();
            for (int i = 0; i < n; i++) {
                List<Integer> oldPerm = perm.poll();
                for (int j = 0; j <= oldPerm.size(); j++) {
                    List<Integer> newPerm = new ArrayList<>(oldPerm);
                    newPerm.add(j, num);
                    if (newPerm.size() == nums.length)
                        result.add(newPerm);
                    else perm.add(newPerm);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Permutations.findPermutations(new int[]{1, 3, 5});
        System.out.print("Here are all the permutations: " + result);
    }
}

class LetterCaseStringPermutation { // medium
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null) return permutations;

        permutations.add(str);
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {    // number skip
                int n = permutations.size();
                for (int j = 0; j < n; j++) {
                    char[] chs = permutations.get(j).toCharArray();

                    if (Character.isUpperCase(chs[i])) chs[i] = Character.toLowerCase(chs[i]);
                    else chs[i] = Character.toUpperCase(chs[i]);

                    permutations.add(String.valueOf(chs));
                }
            }
        }

        return permutations;
    }

    public static void main(String[] args) {
        List<String> result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ad52");
        System.out.println(" String permutations are: " + result);

        result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);
    }
}

// hard
class ParenthessString {
    String str;
    int openCnt;
    int closeCnt;

    public ParenthessString(String s, int openCnt, int closeCnt) {
        this.str = s;
        this.openCnt = openCnt;
        this.closeCnt = closeCnt;
    }
}

class GenerateParentheses {
    public static List<String> generateValidParentheses(int num) {
        List<String> result = new ArrayList<String>();
        Queue<ParenthessString> queue = new LinkedList<>();

        queue.add(new ParenthessString("", 0, 0));
        while (!queue.isEmpty()) {
            ParenthessString ps = queue.poll();
            if (ps.openCnt == num && ps.closeCnt == num) result.add(ps.str);
            else {
                if (ps.openCnt < num) queue.add(new ParenthessString(ps.str + "(", ps.openCnt + 1, ps.closeCnt));
                if (ps.openCnt > ps.closeCnt)
                    queue.add(new ParenthessString(ps.str + ")", ps.openCnt, ps.closeCnt + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> result = GenerateParentheses.generateValidParentheses(2);
        System.out.println("All combinations of balanced parentheses are: " + result);

        result = GenerateParentheses.generateValidParentheses(3);
        System.out.println("All combinations of balanced parentheses are: " + result);
    }
}

// hard
class AbbreviatedWord {
    StringBuilder str;
    int start;
    int count;

    public AbbreviatedWord(StringBuilder str, int start, int count) {
        this.str = str;
        this.start = start;
        this.count = count;
    }
}

class GeneralizedAbbreviation {
    public static List<String> generateGeneralizedAbbreviation(String word) {
        List<String> result = new ArrayList<String>();
        Queue<AbbreviatedWord> queue = new LinkedList<>();
        queue.add(new AbbreviatedWord(new StringBuilder(), 0, 0));
        while (!queue.isEmpty()) {
            AbbreviatedWord abWord = queue.poll();
            if (abWord.start == word.length()) {
                if (abWord.count != 0) abWord.str.append(abWord.count);
                result.add(abWord.str.toString());
            } else {
                queue.add(new AbbreviatedWord(new StringBuilder(abWord.str), abWord.start + 1, abWord.count + 1));

                if (abWord.count != 0) abWord.str.append(abWord.count);
                queue.add(new AbbreviatedWord(new StringBuilder(abWord.str).append(word.charAt(abWord.start)), abWord.start + 1, 0));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> result = GeneralizedAbbreviation.generateGeneralizedAbbreviation("BAT");
        System.out.println("Generalized abbreviation are: " + result);

        result = GeneralizedAbbreviation.generateGeneralizedAbbreviation("code");
        System.out.println("Generalized abbreviation are: " + result);
    }
}

// challenge 1
class EvaluateExpression {
    public static List<Integer> diffWaysToEvaluateExpression(String input) {
        List<Integer> result = new ArrayList<>();
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) result.add(Integer.parseInt(input));
        else {
            for (int i = 0; i < input.length(); i++) {
                char chr = input.charAt(i);
                if (!Character.isDigit(chr)) {  // char is operator
                    List<Integer> left = diffWaysToEvaluateExpression(input.substring(0, i));
                    List<Integer> right = diffWaysToEvaluateExpression(input.substring(i + 1));
                    for (int part1 : left) {
                        for (int part2 : right) {
                            if (chr == '+') result.add(part1 + part2);
                            else if (chr == '-') result.add(part1 - part2);
                            else if (chr == '*') result.add(part1 * part2);
                        }
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = EvaluateExpression.diffWaysToEvaluateExpression("1+2*3");
        System.out.println("Expression evaluations: " + result);

        result = EvaluateExpression.diffWaysToEvaluateExpression("2*3-4-5");
        System.out.println("Expression evaluations: " + result);
    }
}

// challenge 2
class UniqueTrees {
    public static List<TreeNode> findUniqueTrees(int n) {
        if (n <= 0) return new ArrayList<TreeNode>();
        else return findUniqueTreesRecursive(1, n);
    }

    public static List<TreeNode> findUniqueTreesRecursive(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) result.add(null);  // should return 'null' for the left and right child
        else {
            for (int i = start; i <= end; i++) {
                // making 'i' root of the tree
                List<TreeNode> left = findUniqueTreesRecursive(start, i - 1);
                List<TreeNode> right = findUniqueTreesRecursive(i + 1, end);
                for (TreeNode leftTree : left) {
                    for (TreeNode rightTree : right) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftTree;
                        root.right = rightTree;
                        result.add(root);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<TreeNode> result = UniqueTrees.findUniqueTrees(2);
        System.out.print("Total trees: " + result.size());
    }
}

// challenge 3
class CountUniqueTrees {
    public int countTrees(int n) {
        if (n <= 1) return 1;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // making 'i' root of the tree
            int countOfLeftSubtrees = countTrees(i - 1);
            int countOfRightSubtrees = countTrees(n - i);
            count += (countOfLeftSubtrees * countOfRightSubtrees);
        }
        return count;
    }

    public static void main(String[] args) {
        CountUniqueTrees ct = new CountUniqueTrees();
        int count = ct.countTrees(3);
        System.out.print("Total trees: " + count);
    }
}