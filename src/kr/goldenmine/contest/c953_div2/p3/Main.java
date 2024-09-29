package kr.goldenmine.contest.c953_div2.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int T = scan.nextInt();

        while(T-- > 0) {
            int N = scan.nextInt();
            long M = scan.nextLong();

            int[] arr = new int[N + 1];
            for(int i = 0; i <= N; i++) {
                arr[i] = i;
            }

            // 거리가 2인 애를 스왑하면
            // 4 3 2 1
            //
            // 최댓값 = N부터 1까지
            // 최솟값 = 1부터 N까지
            // 투 포인터 같은디
            int left = 1;
            int right = N;
            int value = 0;

            // left가 커지면 1씩 감소하는 방향
            // right가 커지면 1씩 증가하는 방향
            // 1 2 3 4 5
            // 5 2 3 4 1 -> 4증가
            // 1 5 3 4 2 -> 3증가 right - left만큼 증가... 하나?
            while(left < right) {
                int beforeLeft = Math.abs(arr[left] - left);
                int beforeRight = Math.abs(arr[right] - right);

                int nextLeft = Math.abs(arr[right] - left);
                int nextRight = Math.abs(arr[left] - right);

                int diff = nextRight - beforeRight + nextLeft - beforeLeft;

                if(M > value + diff) { // 바꿔도 여전히 작으면
                    swap(arr, left, right); // 둘이 바꾸고 right 감소
                    right--;
                } else if(M == value + diff) {
                    swap(arr, left, right);
                    System.out.println(Arrays.toString(arr));
                    break;
                }
            }
            System.out.println("end " + Arrays.toString(arr));
        }
    }
}
