import java.util.*;

public class MergeInterval {
}

class Interval {    // init
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class MergeIntervals {  // medium
    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> mergedIntervals = new LinkedList<>();
        // TODO: Write your code here
        Collections.sort(intervals, new Comparator<Interval>() {    // start순으로 정렬
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        int start = intervals.get(0).start, end = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {    // start로 정렬된 상태
            if (intervals.get(i).end <= end) continue;  // A가 B를 포함
            else if (intervals.get(i).start <= end) {   // A와 B가 걸침
                end = intervals.get(i).end;
            } else {    // A와 B가 분리
                mergedIntervals.add(new Interval(start, end));
                start = intervals.get(i).start;
                end = intervals.get(i).end;
            }
        }
        mergedIntervals.add(new Interval(start, end));   // 마지막 추가

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }
}

class InsertInterval {  // medium
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> mergedIntervals = new ArrayList<>();
        //TODO: Write your code here
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).end < newInterval.start
                    || intervals.get(i).start > newInterval.end) {   // 안겹침
                mergedIntervals.add(intervals.get(i));
                continue;
            } else if (intervals.get(i).start <= newInterval.start
                    && intervals.get(i).end >= newInterval.end) return intervals;    // 포함
            // 겹치는 부분이 있으면
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
        }
        mergedIntervals.add(newInterval);

        Collections.sort(mergedIntervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }
}

class IntervalsIntersection {   // medium
    public static Interval[] merge(Interval[] arr1, Interval[] arr2) {
        List<Interval> intervalsIntersection = new ArrayList<Interval>();
        // TODO: Write your code here
        int idx1 = 0, idx2 = 0;
        while (idx1 < arr1.length && idx2 < arr2.length) {
            int start1 = arr1[idx1].start, end1 = arr1[idx1].end;
            int start2 = arr2[idx2].start, end2 = arr2[idx2].end;
            if (end1 < start2) idx1++;
            else if (end2 < start1) idx2++;
            else {  // 겹치는게 있을 때
                int start = Math.max(start1, start2);
                int end = Math.min(end1, end2);
                intervalsIntersection.add(new Interval(start, end));
                idx1++;
            }
        }

        return intervalsIntersection.toArray(new Interval[intervalsIntersection.size()]);
    }

    public static void main(String[] args) {
        Interval[] input1 = new Interval[]{new Interval(1, 3), new Interval(5, 6), new Interval(7, 9)};
        Interval[] input2 = new Interval[]{new Interval(2, 3), new Interval(5, 7)};
        Interval[] result = IntervalsIntersection.merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input1 = new Interval[]{new Interval(1, 3), new Interval(5, 7), new Interval(9, 12)};
        input2 = new Interval[]{new Interval(5, 10)};
        result = IntervalsIntersection.merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
    }
}

class ConflictingAppointments { // medium
    public static boolean canAttendAllAppointments(Interval[] intervals) {
        // TODO: Write your code here
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        int lastEnd = intervals[0].end;
        for (int i = 1; i < intervals.length; i++) {
            if (lastEnd > intervals[i].start) return false;
            else lastEnd = intervals[i].end;
        }

        return true;
    }

    public static void main(String[] args) {
        Interval[] intervals = {new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)};
        boolean result = ConflictingAppointments.canAttendAllAppointments(intervals);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals1 = {new Interval(6, 7), new Interval(2, 4), new Interval(8, 12)};
        result = ConflictingAppointments.canAttendAllAppointments(intervals1);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals2 = {new Interval(4, 5), new Interval(2, 3), new Interval(3, 6)};
        result = ConflictingAppointments.canAttendAllAppointments(intervals2);
        System.out.println("Can attend all appointments: " + result);
    }
}

// challenge 1
class Meeting {
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class MinimumMeetingRooms {
    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        int ans = 1;
        Collections.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.start - o2.start;
            }
        });
        PriorityQueue<Meeting> pq = new PriorityQueue<>(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.end - o2.end;
            }
        });
        for (Meeting meeting : meetings) {
            while (!pq.isEmpty() && meeting.start >= pq.peek().end) pq.poll();   // 지난 회의 제거
            pq.add(meeting);    // 회의 추가
            ans = Math.max(ans, pq.size()); // 값 갱신
        }

        return ans;
    }

    public static void main(String[] args) {
        List<Meeting> input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        int result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 5));
                add(new Meeting(7, 9));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(6, 7));
                add(new Meeting(2, 4));
                add(new Meeting(8, 12));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 3));
                add(new Meeting(3, 6));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);
    }
}

// challenge 2
class Job {
    int start;
    int end;
    int cpuLoad;

    public Job(int start, int end, int cpuLoad) {
        this.start = start;
        this.end = end;
        this.cpuLoad = cpuLoad;
    }
};

class MaximumCPULoad {
    public static int findMaxCPULoad(List<Job> jobs) {
        int ans = 0;
        int currentLoad = 0;
        Collections.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.start - o2.start;
            }
        });
        PriorityQueue<Job> pq = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.end - o2.end;
            }
        });
        for (Job job : jobs) {
            while (!pq.isEmpty() && job.start >= pq.peek().end) {
                currentLoad -= pq.peek().cpuLoad;
                pq.poll();
            }
            pq.add(job);
            currentLoad += job.cpuLoad;
            ans = Math.max(ans, currentLoad);
        }

        return ans;
    }

    public static void main(String[] args) {
        List<Job> input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
        System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
        System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
        System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoad(input));
    }
}

// challenge 3
class EmployeeFreeTime {
    public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        for (int i = 0; i < schedule.size(); i++) {
            pq.addAll(schedule.get(i));
        }
        Interval first = pq.poll();
        int start = first.start;
        int end = first.end;

        while (!pq.isEmpty()) {
            Interval now = pq.poll();
            if (now.start > end) {    // 안겹칠 때
                result.add(new Interval(end, now.start));
                start = now.start;
            }
            end = now.end;
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Interval>> input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
        List<Interval> result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(6, 8))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(1, 3))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<Interval>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
        result = EmployeeFreeTime.findEmployeeFreeTime(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
    }
}