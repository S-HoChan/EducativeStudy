import java.util.*;

class CyclicSort {  // easy
    public static void sort(int[] nums) {
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] == idx + 1) idx++;
            else {
                int temp = nums[idx];
                nums[idx] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
    }
}

class MissingNumber {   // easy
    public static int findMissingNumber(int[] nums) {
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] < nums.length && nums[idx] != idx) {
                int temp = nums[idx];
                nums[idx] = nums[temp];
                nums[temp] = temp;
            } else idx++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return -1;
    }
}

class AllMissingNumbers {   // easy
    public static List<Integer> findNumbers(int[] nums) {
        List<Integer> missingNumbers = new ArrayList<>();
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] != nums[nums[idx] - 1]) {
                int temp = nums[idx];
                nums[idx] = nums[temp - 1];
                nums[temp - 1] = temp;
            } else idx++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) missingNumbers.add(i + 1);
        }

        return missingNumbers;
    }
}

class FindDuplicate {   // easy
    public static int findNumber(int[] nums) {
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] != idx + 1) {
                if (nums[idx] != nums[nums[idx] - 1]) { // change
                    int temp = nums[idx];
                    nums[idx] = nums[temp - 1];
                    nums[temp - 1] = temp;
                } else return nums[idx];
            } else idx++;
        }

        return -1;
    }
}

class FindAllDuplicate {    // easy
    public static List<Integer> findNumbers(int[] nums) {
        List<Integer> duplicateNumbers = new ArrayList<>();
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] != nums[nums[idx] - 1]) {
                int temp = nums[idx];
                nums[idx] = nums[temp - 1];
                nums[temp - 1] = temp;
            } else idx++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) duplicateNumbers.add(nums[i]);
        }

        return duplicateNumbers;
    }
}

class FindCorruptNums { // challenge 1
    public static int[] findNumbers(int[] nums) {
        int ans[] = new int[2];
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] != nums[nums[idx] - 1]) {
                int temp = nums[idx];
                nums[idx] = nums[temp - 1];
                nums[temp - 1] = temp;
            } else idx++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ans[0] = nums[i];
                ans[1] = i + 1;
            }
        }
        return ans;
    }
}

class FirstSmallestMissingPositive {    // challenge 2
    public static int findNumber(int[] nums) {
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] > 0 && nums[idx] < nums.length && nums[idx] != nums[nums[idx] - 1]) {
                int temp = nums[idx];
                nums[idx] = nums[temp - 1];
                nums[temp - 1] = temp;
            } else idx++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return -1;
    }
}

class FirstKMissingPositive {   // challenge 3
    public static List<Integer> findNumbers(int[] nums, int k) {
        List<Integer> missingNumbers = new ArrayList<>();
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] > 0 && nums[idx] <= nums.length && nums[idx] != nums[nums[idx] - 1]) {
                int temp = nums[idx];
                nums[idx] = nums[temp - 1];
                nums[temp - 1] = temp;
            } else idx++;
        }

        Set<Integer> extraNumbers = new HashSet<>();
        for (int i = 0; i < nums.length && missingNumbers.size() < k; i++) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1);
                extraNumbers.add(nums[i]);
            }
        }

        for (int i = 1; missingNumbers.size() < k; i++) {
            int num = i + nums.length;
            if (!extraNumbers.contains(num)) missingNumbers.add(num);
        }

        return missingNumbers;
    }
}