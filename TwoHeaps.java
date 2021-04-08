import java.io.PipedReader;
import java.util.*;

public class TwoHeaps {
}

class MedianOfAStream { // medium
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public MedianOfAStream() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void insertNum(int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) maxHeap.add(num);
        else minHeap.add(num);

        // both heaps have equal number of elements or maxHeap have one more element
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size())
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianOfAStream medianOfAStream = new MedianOfAStream();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
    }
}

class SlidingWindowMedian { // hard
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public double[] findSlidingWindowMedian(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        // TODO: Write your code here
        for (int i = 0; i < nums.length; i++) {
            if (maxHeap.isEmpty() || maxHeap.peek() >= nums[i]) maxHeap.add(nums[i]);
            else minHeap.add(nums[i]);

            // rebalancing
            if (maxHeap.size() > minHeap.size() + 1)
                minHeap.add(maxHeap.poll());
            else if (maxHeap.size() < minHeap.size())
                maxHeap.add(minHeap.poll());

            if (i - k + 1 >= 0) {
                if (maxHeap.size() == minHeap.size())
                    result[i - k + 1] = (maxHeap.peek() + minHeap.peek()) / 2.0;
                else result[i - k + 1] = maxHeap.peek();

                // remove nums[i - k + 1];
                int removeNum = nums[i - k + 1];
                if (removeNum <= maxHeap.peek()) maxHeap.remove(removeNum);
                else minHeap.remove(removeNum);

                // rebalancing
                if (maxHeap.size() > minHeap.size() + 1)
                    minHeap.add(maxHeap.poll());
                else if (maxHeap.size() < minHeap.size())
                    maxHeap.add(minHeap.poll());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
        double[] result = slidingWindowMedian.findSlidingWindowMedian(new int[]{1, 2, -1, 3, 5}, 2);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
        System.out.println();

        slidingWindowMedian = new SlidingWindowMedian();
        result = slidingWindowMedian.findSlidingWindowMedian(new int[]{1, 2, -1, 3, 5}, 3);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
    }
}

class MaximizeCapital { // hard
    public static int findMaximumCapital(int[] capital, int[] profits, int numberOfProjects, int initialCapital) {
        PriorityQueue<Integer> minCapitalHeap = new PriorityQueue<>((i1, i2) -> capital[i1] - capital[i2]);
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>((i1, i2) -> profits[i2] - profits[i1]);

        // init
        for (int i = 0; i < capital.length; i++)
            minCapitalHeap.add(i);
        int availableCapital = initialCapital;

        for (int i = 0; i < numberOfProjects; i++) {    // operate
            while (!minCapitalHeap.isEmpty() && capital[minCapitalHeap.peek()] <= availableCapital)
                maxProfitHeap.add(minCapitalHeap.poll());

            if (maxProfitHeap.isEmpty()) break; // if we can't any project

            availableCapital += profits[maxProfitHeap.poll()];
        }

        return availableCapital;
    }

    public static void main(String[] args) {
        int result = MaximizeCapital.findMaximumCapital(new int[]{0, 1, 2}, new int[]{1, 2, 3}, 2, 1);
        System.out.println("Maximum capital: " + result);
        result = MaximizeCapital.findMaximumCapital(new int[]{0, 1, 2, 3}, new int[]{1, 2, 3, 5}, 3, 0);
        System.out.println("Maximum capital: " + result);
    }
}

class NextInterval {    // challenge 1
    public static int[] findNextInterval(Interval[] intervals) {
        int[] result = new int[intervals.length];
        int n = intervals.length;
        PriorityQueue<Integer> maxStartHeap = new PriorityQueue<>(n, (i1, i2) -> intervals[i2].start - intervals[i1].start);
        PriorityQueue<Integer> maxEndHeap = new PriorityQueue<>(n, (i1, i2) -> intervals[i2].end - intervals[i1].end);
        for (int i = 0; i < n; i++) {
            maxStartHeap.add(i);
            maxEndHeap.add(i);
        }

        for (int i = 0; i < n; i++) {
            int topEnd = maxEndHeap.poll();
            if (intervals[maxStartHeap.peek()].start >= intervals[topEnd].end) {
                int topStart = maxStartHeap.poll();
                while (!maxStartHeap.isEmpty() && intervals[maxStartHeap.peek()].start >= intervals[topEnd].end) {
                    topStart = maxStartHeap.poll();
                }
                result[topEnd] = topStart;
                maxStartHeap.add(topStart);
            } else result[topEnd] = -1;
        }

        return result;
    }

    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{new Interval(2, 3), new Interval(3, 4), new Interval(5, 6)};
        int[] result = NextInterval.findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");
        System.out.println();

        intervals = new Interval[]{new Interval(3, 4), new Interval(1, 5), new Interval(4, 6)};
        result = NextInterval.findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");
    }
}