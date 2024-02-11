package kr.goldenmine.baekjoon.silver.silver4.p10845S;

import java.io.*;
import java.util.ArrayDeque;
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

        int N = scan.nextInt();

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        for(int i = 0; i < N; i++) {
            String command = scan.next();

            switch (command) {
                case "push": {
                    int value = scan.nextInt();
                    queue.addLast(value);
                    break;
                }

                case "front": {
                    if(!queue.isEmpty())
                        output.write(String.valueOf(queue.getFirst()));
                    else
                        output.write("-1");
                    output.newLine();
                    break;
                }

                case "back": {
                    if(!queue.isEmpty())
                        output.write(String.valueOf(queue.getLast()));
                    else
                        output.write("-1");
                    output.newLine();
                    break;
                }

                case "size": {
                    output.write(String.valueOf(queue.size()));
                    output.newLine();
                    break;
                }

                case "empty": {
                    output.write(queue.isEmpty() ? "1" : "0");
                    output.newLine();
                    break;
                }

                case "pop": {
                    if(!queue.isEmpty())
                        output.write(String.valueOf(queue.removeFirst()));
                    else
                        output.write("-1");
                    output.newLine();
                    break;
                }
            }
        }

        output.flush();
    }
}
