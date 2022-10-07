package kr.goldenmine.silver.silver1.p1931A_greedy_meeting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static class Meeting {
        int start;
        int finish;

        public Meeting(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        PriorityQueue<Meeting> meetings = new PriorityQueue<>(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if(o1.finish == o2.finish) {
                    return o1.start - o2.start;
                } else {
                    return o1.finish - o2.finish;
                }
            }
        });

        for(int i = 0; i < N; i++) {
            int start = scan.nextInt();
            int finish = scan.nextInt();

            meetings.add(new Meeting(start, finish));
        }

        int lastFinish = 0;
        int total = 0;

        while(!meetings.isEmpty()) {
            Meeting meeting = meetings.poll();

            if(lastFinish <= meeting.start) {
                total++;
                lastFinish = meeting.finish;
            }
        }

        System.out.println(total);
    }
}
