package kr.goldenmine.baekjoon.gold.gold2.p22345;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

    static class Data {
        int position;
        int times;

        public Data(int position, int times) {
            this.position = position;
            this.times = times;
        }
    }

    public static int lowerBound(List<Data> arr, int key) {
        int lo = 0;
        int hi = arr.size();

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            /*
             *  key 값이 중간 위치의 값보다 작거나 같을 경우
             *
             *  (중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.)
             */
            if (key <= arr.get(mid).position) {
                hi = mid;
            }

            else {
                lo = mid + 1;
            }

        }

        return lo;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int I = scan.nextInt();
        int J = scan.nextInt();

        List<Data> people = new ArrayList<>();


        for(int i = 0; i < I; i++) {
            int a = scan.nextInt();
            int x = scan.nextInt();
            people.add(new Data(x, a));
        }

        people.sort(Comparator.comparingInt(o -> o.position));

        int[] sum = new int[I];

        for(int i = 0; i < I; i++) {
            Data data = people.get(i);
            sum[i] = (i > 0 ? sum[i - 1] : 0) + data.position * data.times;
        }

        System.out.println(Arrays.toString(sum));
    }
}
