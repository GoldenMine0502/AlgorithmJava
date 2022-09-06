package kr.goldenmine.silver.silver5.p11723;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
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

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        int M = scan.nextInt();
        /*
            add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
            remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
            check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
            toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
            all: S를 {1, 2, ..., 20} 으로 바꾼다.
            empty: S를 공집합으로 바꾼다.
         */

        int[] arr = new int[21];

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        for(int i = 0; i < M; i++) {
            String command = scan.next();

            switch(command) {
                case "add": {
                    int N = scan.nextInt();

                    arr[N] = 1;
                }
                    break;
                case "remove": {
                    int N = scan.nextInt();

                    arr[N] = 0;
                }

                    break;
                case "check": {
                    int N = scan.nextInt();

                    output.write(String.valueOf(arr[N]));
                    output.newLine();
                }
                    break;
                case "toggle": {
                    int N = scan.nextInt();

                    if(arr[N] == 1) {
                        arr[N] = 0;
                    } else {
                        arr[N] = 1;
                    }
                }
                    break;
                case "all":
                    for(int j = 1; j <= 20; j++) {
                        arr[j] = 1;
                    }
                    break;
                case "empty":
                    for(int j = 1; j <= 20; j++) {
                        arr[j] = 0;
                    }
                    break;
            }
        }

        output.flush();
    }
}
