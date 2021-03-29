import java.util.*;

public class TwoPointers {
}

class PairWithTargetSum {   // easy
    public static int[] search(int[] arr, int targetSum) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == targetSum) break;
            else if (sum > targetSum) j--;
            else i++;
        }

        return new int[]{i, j};
    }
}

class RemoveDuplicates {    // easy
    public static int remove(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            set.add(arr[i++]);
            set.add(arr[j--]);
        }

        return set.size();
    }
}

class SortedArraySquares {  // easy
    public static int[] makeSquares(int[] arr) {
        int[] squares = new int[arr.length];
        int idx = arr.length - 1;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int leftNum = arr[left] * arr[left];
            int rightNum = arr[right] * arr[right];
            if (leftNum > rightNum) {
                squares[idx--] = leftNum;
                left++;
            } else {
                squares[idx--] = rightNum;
                right--;
            }
        }

        return squares;
    }
}

class TripletSumToZero {    // medium
    public static List<List<Integer>> searchTriplets(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            int targetNum = -arr[i];
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum == targetNum) {
                    List<Integer> list = new ArrayList<>();
                    list.add(arr[i]);
                    list.add(arr[left++]);
                    list.add(arr[right--]);
                    triplets.add(list);
                    while (left < right && arr[left] == arr[left - 1]) left++;  // 중복된 left 생략
                    while (left < right && arr[right] == arr[right + 1]) right--;   // 중복된 right 생략
                } else if (sum > targetNum) right--;
                else left++;
            }
        }

        return triplets;
    }
}

class TripletSumCloseToTarget { // medium
    public static int searchTriplet(int[] arr, int targetSum) {
        int ans = -targetSum;
        int len = targetSum - ans;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            int sum = 0;
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                sum = arr[i] + arr[left] + arr[right];
                if (sum == targetSum) return targetSum;
                else if (sum > targetSum) right--;
                else left++;
            }
            if (len > Math.abs(targetSum - sum)) {
                ans = sum;
                len = targetSum - ans;
            } else if (len == targetSum - sum) {
                ans = Math.min(ans, sum);
            }
        }

        return ans;
    }
}

class TripletWithSmallerSum {   // medium
    public static int searchTriplets(int[] arr, int target) {
        int count = 0;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                if (arr[i] + arr[left] + arr[right] < target) {
                    count += right - left;
                    left++;
                } else right--;
            }
        }

        return count;
    }
}

class SubarrayProductLessThanK {    // medium
    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        List<List<Integer>> subarrays = new ArrayList<>();

        for (int left = 0; left < arr.length; left++) { // 2 5 3 10, target : 40
            int sum = arr[left];
            if (sum >= target) continue;
            List<Integer> list = new ArrayList<>();
            list.add(arr[left]);
            List<Integer> subList = new ArrayList<>(list);
            subarrays.add(subList);
            int right = left + 1;
            while (right < arr.length) {
                sum *= arr[right];
                if (sum >= target) break;
                list.add(arr[right++]);
                subList = new ArrayList<>(list);
                subarrays.add(subList);
            }
        }

        return subarrays;
    }
}

class DutchFlag {   // medium
    public static void sort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        for (int i = 0; i <= right; ) {
            if (arr[i] == 0) swap(arr, i++, left++);
            else if (arr[i] == 1) i++;
            else swap(arr, i, right--);
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

class QuadrupleSumToTarget {    // challenge 1
    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 3; i++) {
            if (arr[i] > target) break; // skip first element bigger than target
            if (i > 0 && arr[i] == arr[i - 1]) continue;    // skip same element
            for (int j = i + 1; j < arr.length - 2; j++) {
                if (arr[i] + arr[j] > target) break;    // skip second element bigger than target
                if (j > i + 1 && arr[j] == arr[j - 1]) continue;    // skip same element
                int left = j + 1;
                int right = arr.length - 1;
                while (left < right) {
                    int currentSum = arr[i] + arr[j] + arr[left] + arr[right];
                    if (currentSum == target) {
                        List<Integer> tempList = new ArrayList<>();
                        tempList.add(arr[i]);
                        tempList.add(arr[j]);
                        tempList.add(arr[left]);
                        tempList.add(arr[right]);
                        quadruplets.add(tempList);
                        left++;
                        right--;
                        while (left < right && arr[left] == arr[left - 1]) left++;  // skip same element
                        while (left < right && arr[right] == arr[right + 1]) right--;
                    } else if (currentSum > target) right--;
                    else left++;
                }
            }
        }

        return quadruplets;
    }
}

class BackspaceCompare {    // challenge 2
    public static boolean compare(String str1, String str2) {
        Stack<Character> s1 = new Stack<>();
        for (int i = 0; i < str1.length(); i++) {
            char now = str1.charAt(i);
            if (now == '#') {
                if (!s1.isEmpty()) s1.pop();
            } else {
                s1.add(now);
            }
        }
        StringBuilder sb1 = new StringBuilder();
        while (!s1.isEmpty()) sb1.append(s1.pop());

        Stack<Character> s2 = new Stack<>();
        for (int i = 0; i < str2.length(); i++) {
            char now = str2.charAt(i);
            if (now == '#') {
                if (!s2.isEmpty()) s2.pop();
            } else {
                s2.add(now);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        while (!s2.isEmpty()) sb2.append(s2.pop());

        return sb1.toString().equals(sb2.toString());
    }
}

class ShortestWindowSort {  // challenge 3
    public static int sort(int[] arr) {
        int left = 0, right = arr.length - 1;
        int max = arr[left], min = arr[right];
        // sort range
        while (left < arr.length - 1) {
            if (arr[left] > arr[left + 1]) break;
            else {
                left++;
                min = Math.min(min, arr[left]);
                max = Math.max(max, arr[left]);
            }
        }
        while (right > 1) {
            if (arr[right] < arr[right - 1]) break;
            else {
                right--;
                min = Math.min(min, arr[right]);
                max = Math.min(max, arr[right]);
            }
        }

        // additional
        while (left > 0) {
            if (arr[left - 1] > min) left--;
            else break;
        }
        while (right < arr.length - 1) {
            if (arr[right + 1] < max) right++;
            else break;
        }

        return right - left + 1;
    }
}