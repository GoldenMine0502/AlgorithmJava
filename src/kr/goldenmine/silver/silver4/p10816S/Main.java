package kr.goldenmine.silver.silver4.p10816S;

import java.io.*;
import java.util.Arrays;
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

    class Range {
        int min;
        int max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static int lowerBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            /*
             *  key 값이 중간 위치의 값보다 작거나 같을 경우
             *
             *  (중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.)
             */
            if (key <= arr[mid]) {
                hi = mid;
            }

            else {
                lo = mid + 1;
            }

        }

        return lo;
    }

    public static int upperBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            // key값이 중간 위치의 값보다 작을 경우
            if (key < arr[mid]) {
                hi = mid;
            }
            // 중복원소의 경우 else에서 처리된다.
            else {
                lo = mid + 1;
            }

        }

        return lo;
    }

    // 내림차순 기준
    public static int binarySearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;

        while(true) {
            int mid = (left + right) / 2;
            if(arr[mid] == value) {
                return mid;
            } else if(arr[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

            if(left > right) return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        Arrays.sort(arr);

        int M = scan.nextInt();

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        for(int i = 0; i < M; i++) {
            int value = scan.nextInt();

            int counts = upperBound(arr, value) - lowerBound(arr, value);

            output.write(String.valueOf(counts));
            output.write(" ");

//            System.out.print(upperBound(arr, value) + ", " + lowerBound(arr, value) + " / ");

            // 결국 시간초과나더라
//            int index = binarySearch(arr, value);
//            if(index >= 0) {
//                int leftIndex = index;
//                int rightIndex = index;
//
//                // 9 10 10 10 11
//                // 인덱스 2면
//                // 0~4를 가르킴
//                // 같은 갯수 몇개인지 세기
//                while(leftIndex >= 1 && arr[leftIndex - 1] == value) {
//                    leftIndex--;
//                }
//                while(rightIndex < arr.length - 1 && arr[rightIndex + 1] == value) {
//                    rightIndex++;
//                }
//
//                // 이러면 1~3을 가르킴
//                int count = (rightIndex - index) + (index - leftIndex) + 1;
//
//                output.write(String.valueOf(count));
//                output.write(" ");
//            } else {
//                output.write("0 ");
//            }
        }

        output.flush();
    }
}
