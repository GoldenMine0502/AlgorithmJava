package kr.goldenmine.platinum.platinum5.p6549_3A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static long getArea(long[] histogram, int left, int right) {

        // 막대 폭(넓이)이 1일경우 높이가 넓이가 되므로 바로 반환
        if(left == right) {
            return histogram[left];
        }

        // [1번 과정]
        int mid = (left + right) / 2;	// 중간지점

        /*
         *  [2번 과정]
         *  mid를 기점으로 양쪽으로 나누어 양쪽 구간 중 큰 넓이를 저장
         *  왼쪽 부분 : lo ~ mid
         *  오른쪽 부분 : mid + 1 ~ hi
         */
        long leftArea = getArea(histogram, left, mid);
        long rightArea = getArea(histogram, mid + 1, right);

        // [3번 과정]
        long max = Math.max(leftArea, rightArea);


        /*
         * 4번 과정(lo~hi 구간의 최대 넓이)을 구현해야함
         */

        long midArea = getMidArea(histogram, left, right, mid);

        return Math.max(max, midArea);

    }

    // 중간지점 영역의 넓이를 구하는 메소드
    public static long getMidArea(long[] histogram, int lo, int hi, int mid) {

        int toLeft = mid;	// 중간 지점으로부터 왼쪽으로 갈 변수
        int toRight = mid;	// 중간 지점으로부터 오른쪽으로 갈 변수

        long height = histogram[mid];	// 높이

        // [초기 넓이 (구간 폭이 1이므로 넓이는 높이가 면적이 됨)
        long maxArea = height;


        // 양 끝 범위를 벗어나기 전까지 반복
        while(lo < toLeft && toRight < hi) {

            /*
             *  양쪽 다음칸의 높이 중 높은 값을 선택하되,
             *  갱신되는 height는 기존 height보다 작거나 같아야 한다.
             */
            if(histogram[toLeft - 1] < histogram[toRight + 1]) {
                toRight++;
                height = Math.min(height, histogram[toRight]);
            }
            else {
                toLeft--;
                height = Math.min(height, histogram[toLeft]);
            }

            // 최대 넓이 갱신
            maxArea = Math.max(maxArea, height * (toRight - toLeft + 1));
        }


        // 오른쪽 구간을 끝까지 탐색 못했다면 마저 탐색한다.
        while(toRight < hi) {
            toRight++;
            height = Math.min(height, histogram[toRight]);
            maxArea = Math.max(maxArea, height * (toRight - toLeft + 1));
        }

        // 왼쪽 구간을 끝까지 탐색 못했다면 마저 탐색한다.
        while(lo < toLeft) {
            toLeft--;
            height = Math.min(height, histogram[toLeft]);
            maxArea = Math.max(maxArea, height * (toRight - toLeft + 1));
        }

        return maxArea;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        String line;

        while (!(line = scan.nextLine()).equals("0")) {
            String[] split = line.split(" ");

            long[] arr = new long[split.length - 1];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(split[i + 1]);
            }

            long area = getArea(arr, 0, arr.length - 1);
            System.out.println(area);
        }
    }
}
