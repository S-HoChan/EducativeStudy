import java.util.*;

public class SlidingWindow {
}

class MaxSumSubArrayOfSizeK {    // easy
    public static int findMaxSumSubArray(int k, int[] arr) {
        int[] subArr = new int[arr.length - k + 1];
        for (int i = 0; i < arr.length - k + 1; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += arr[j];
            }
            subArr[i] = sum;
        }

        int ans = 0;
        for (int i = 0; i < subArr.length; i++) {
            ans = Math.max(ans, subArr[i]);
        }

        return ans;
    }
}

class MinSizeSubArraySum {    // easy
    public static int findMinSubArray(int S, int[] arr) {
        int ans = arr.length + 1;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum >= S) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }

        if (ans == arr.length + 1) return -1;
        else return ans;
    }
}

class LongestSubstringKDistinct {  // medium
    public static int findLength(String str, int k) {
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);
            int lng = 1;

            ArrayList<Character> list = new ArrayList<>();
            list.add(now);
            for (int j = i + 1; j < str.length(); j++) {
                now = str.charAt(j);
                if (list.contains(now)) {
                    lng++;
                } else {
                    if (list.size() + 1 <= k) {
                        list.add(now);
                        lng++;
                    } else break;
                }
            }
            ans = Math.max(ans, lng);
        }

        return ans;
    }
}

class MaxFruitCountOf2Types {    // medium
    public static int findLength(char[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            char now = arr[i];
            int ae = 1;
            char other = ' ';
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] == now || arr[j] == other) ae++;
                else if (other == ' ') {
                    other = arr[j];
                    ae++;
                } else break;
            }
            ans = Math.max(ans, ae);
        }

        return ans;
    }
}

class NoRepeatSubstring { // hard
    public static int findLength(String str) {
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);
            int len = 1;
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(j) == now) {
                    ans = Math.max(ans, len);
                    break;
                } else {
                    len++;
                    now = str.charAt(j);
                }
            }
        }

        return ans;
    }
}

class CharacterReplacement {    // hard
    public static int findLength(String str, int k) {
        int ans = 0;

        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);
            int cnt = 0;
            int len = 1;
            for (int j = i + 1; j < str.length(); j++) {
                if (now == str.charAt(j)) {
                    len++;
                } else if (cnt + 1 <= k) {
                    cnt++;
                    len++;
                } else break;
            }
            ans = Math.max(ans, len);
        }

        return ans;
    }
}

class ReplacingOnes {   // hard
    public static int findLength(int[] arr, int k) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int len = 0;
            int cnt = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] == 1) {
                    len++;
                } else {    // arr[j] == 0
                    if (cnt + 1 <= k) {
                        len++;
                        cnt++;
                    } else break;
                }
            }
            ans = Math.max(ans, len);
        }

        return ans;
    }
}

class StringPermutation {   // challenge 1
    public static boolean findPermutation(String str, String pattern) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char now = pattern.charAt(i);
            if (map.containsKey(now)) {
                map.put(now, map.get(now) + 1);
            } else {
                map.put(now, 1);
            }
        }

        for (int i = 0; i < str.length() - pattern.length() + 1; i++) {
            HashMap<Character, Integer> maps = new HashMap<>();
            maps.putAll(map);

            for (int j = i; j < str.length(); j++) {
                char now = str.charAt(j);
                if (!maps.containsKey(now)) break;
                else if (maps.get(now) == 1) maps.remove(now);
                else maps.put(now, maps.get(now) - 1);

                if (maps.isEmpty()) return true;
            }
        }

        return false;
    }
}

class StringAnagrams {  // challenge 2
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        List<Integer> resultIndices = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char now = pattern.charAt(i);
            if (map.containsKey(map)) map.put(now, map.get(now) + 1);
            else map.put(now, 1);
        }

        for (int i = 0; i < str.length() - pattern.length() + 1; i++) {
            HashMap<Character, Integer> maps = new HashMap<>();
            maps.putAll(map);
            for (int j = i; j < str.length(); j++) {
                char now = str.charAt(j);
                if (!maps.containsKey(now)) break;
                else if (maps.get(now) == 1) {
                    maps.remove(now);
                } else {
                    maps.put(now, maps.get(now) - 1);
                }

                if (maps.isEmpty()) {
                    resultIndices.add(i);
                    break;
                }
            }
        }

        return resultIndices;
    }
}

class MinimumWindowSubstring {  // challenge 3
    public static String findSubstring(String str, String pattern) {
        String ans = "";
        int ansLen = str.length() + 1;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char now = pattern.charAt(i);
            if (map.containsKey(now)) map.put(now, map.get(now) + 1);
            else map.put(now, 1);
        }

        for (int i = 0; i < str.length() - pattern.length() + 1; i++) {
            HashMap<Character, Integer> maps = new HashMap<>();
            maps.putAll(map);
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < str.length(); j++) {
                char now = str.charAt(j);
                sb.append(now);
                if (!maps.containsKey(now)) continue;

                if (maps.get(now) == 1) maps.remove(now);
                else maps.put(now, maps.get(now) - 1);

                if (maps.isEmpty()) {
                    if (sb.length() < ansLen) {
                        ans = sb.toString();
                        ansLen = sb.length();
                    }
                    break;
                }
            }
        }

        return ans;
    }
}

class WordConcatenation {   // challenge 4
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        List<Integer> resultIndices = new ArrayList<>();
        int wordSum = 0;
        for (String word : words) {
            wordSum += word.length();
        }

        for (int i = 0; i < str.length() - wordSum + 1; i++) {
            int idx = i;
            boolean[] checked = new boolean[words.length];

            while (idx < str.length()) {
                int last = idx;
                for (int j = 0; j < words.length; j++) {
                    if (checked[j]) continue;
                    int len = words[j].length();
                    String now = str.substring(idx, idx + len);
                    if (now.equals(words[j])) {
                        idx += len;
                        checked[j] = true;
                        break;
                    }
                }
                if (last == idx) break;
            }
            if (isOk(checked)) resultIndices.add(i);
        }

        return resultIndices;
    }

    public static boolean isOk(boolean[] checked) {
        for (boolean check : checked)
            if (!check) return false;
        return true;
    }
}