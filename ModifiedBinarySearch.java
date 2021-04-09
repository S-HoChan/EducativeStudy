import java.util.*;

public class ModifiedBinarySearch {
}

class BinarySearch {    // easy
    public static int search(int[] arr, int key) {
        int start = 0, end = arr.length - 1;
        boolean isAscending = arr[start] < arr[end];
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == key) return mid;

            if (isAscending) {
                if (key < arr[mid]) end = mid - 1;
                else start = mid + 1;
            } else {
                if (key > arr[mid]) end = mid - 1;
                else start = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(BinarySearch.search(new int[]{4, 6, 10}, 10));
        System.out.println(BinarySearch.search(new int[]{1, 2, 3, 4, 5, 6, 7}, 5));
        System.out.println(BinarySearch.search(new int[]{10, 6, 4}, 10));
        System.out.println(BinarySearch.search(new int[]{10, 6, 4}, 4));
    }
}

class CeilingOfANumber {    // medium
    public static int searchCeilingOfANumber(int[] arr, int key) {
        if (key > arr[arr.length - 1]) return -1;

        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == key) return mid;
            if (arr[mid] < key) start = mid + 1;
            else end = mid - 1;
        }
        return start;
    }

    public static void main(String[] args) {
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[]{4, 6, 10}, 6));
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[]{1, 3, 8, 10, 15}, 12));
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[]{4, 6, 10}, 17));
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[]{4, 6, 10}, -1));
    }
}

class NextLetter {  // medium
    public static char searchNextLetter(char[] letters, char key) {
        int start = 0, end = letters.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (letters[mid] == key) return letters[(mid + 1) % letters.length];
            else if (letters[mid] > key) end = mid - 1;
            else start = mid + 1;
        }

        return letters[start % letters.length];
    }

    public static void main(String[] args) {
        System.out.println(NextLetter.searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'f'));
        System.out.println(NextLetter.searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'b'));
        System.out.println(NextLetter.searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'm'));
        System.out.println(NextLetter.searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'h'));
    }
}

class FindRange {   // medium
    public static int[] findRange(int[] arr, int key) {
        int[] result = new int[]{-1, -1};
        result[0] = search(arr, key, false);
        if (result[0] != -1) // no need to search, if 'key' is not present in the input array
            result[1] = search(arr, key, true);
        return result;
    }

    // modified Binary Search
    private static int search(int[] arr, int key, boolean findMaxIndex) {
        int keyIndex = -1;
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else { // key == arr[mid]
                keyIndex = mid;
                if (findMaxIndex)
                    start = mid + 1; // search ahead to find the last index of 'key'
                else
                    end = mid - 1; // search behind to find the first index of 'key'
            }
        }
        return keyIndex;
    }

    public static void main(String[] args) {
        int[] result = FindRange.findRange(new int[]{4, 6, 6, 6, 9}, 6);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = FindRange.findRange(new int[]{1, 3, 8, 10, 15}, 10);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = FindRange.findRange(new int[]{1, 3, 8, 10, 15}, 12);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    }
}

// medium
class ArrayReader {
    int[] arr;

    ArrayReader(int[] arr) {
        this.arr = arr;
    }

    public int get(int index) {
        if (index >= arr.length) return Integer.MAX_VALUE;
        else return arr[index];
    }
}

class SearchInfiniteSortedArray {
    public static int search(ArrayReader reader, int key) {
        int start = 0, end = 1;
        while (reader.get(end) < key) {
            int newStart = end + 1;
            end += (end - start + 1) * 2; // increase to double the bounds size
            start = newStart;
        }
        return binarySearch(reader, key, start, end);
    }

    private static int binarySearch(ArrayReader reader, int key, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key < reader.get(mid)) end = mid - 1;
            else if (key > reader.get(mid)) start = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayReader reader = new ArrayReader(new int[]{4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30});
        System.out.println(SearchInfiniteSortedArray.search(reader, 16));
        System.out.println(SearchInfiniteSortedArray.search(reader, 11));
        reader = new ArrayReader(new int[]{1, 3, 8, 10, 15});
        System.out.println(SearchInfiniteSortedArray.search(reader, 15));
        System.out.println(SearchInfiniteSortedArray.search(reader, 200));
    }
}

// medium
class MinimumDifference {
    public static int searchMinDiffElement(int[] arr, int key) {
        if (key < arr[0]) return arr[0];
        if (key > arr[arr.length - 1]) return arr[arr.length - 1];
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == key) return arr[mid];
            else if (arr[mid] > key) end = mid - 1;
            else start = mid + 1;
        }

        // start == end + 1;
        if (arr[start] - key > key - arr[end]) return arr[end];
        else return arr[start];
    }

    public static void main(String[] args) {
        System.out.println(MinimumDifference.searchMinDiffElement(new int[]{4, 6, 10}, 7));
        System.out.println(MinimumDifference.searchMinDiffElement(new int[]{4, 6, 10}, 4));
        System.out.println(MinimumDifference.searchMinDiffElement(new int[]{1, 3, 8, 10, 15}, 12));
        System.out.println(MinimumDifference.searchMinDiffElement(new int[]{4, 6, 10}, 17));
    }
}
