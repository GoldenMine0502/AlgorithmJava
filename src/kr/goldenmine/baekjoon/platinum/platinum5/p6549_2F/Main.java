package kr.goldenmine.baekjoon.platinum.platinum5.p6549_2F;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }

        long nextLong() { return Long.parseLong(next()); }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if(st.hasMoreTokens()){
                    str = st.nextToken("\n");
                }
                else{
                    str = br.readLine();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static class Data {
        int index;
        long value;

        public Data(int index, long value) {
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        String line;

        /* 놀랍게도 왼큰수의 응용이였네...

         */
        while (!(line = scan.nextLine()).equals("0")) {
            String[] split = line.split(" ");

            int N = Integer.parseInt(split[0]);
            long[] values = new long[split.length - 1];

            for (int i = 0; i < values.length; i++) {
                values[i] = Integer.parseInt(split[i + 1]);
            }

            Data[] results = new Data[N];
            ArrayDeque<Data> stack = new ArrayDeque<>(N);

            for(int i = 0; i < N; i++) {
                long value = values[i];
                Data result = null;

                // 비지 않을 때까지 계속 반복
                while(!stack.isEmpty()) {
                    Data top = stack.pop();

                    // 만약 큰 값을 찾았다면?
                    // 일단 여기에서 true라면 해당하는 값보다 가까운 최대의 값일듯
                    if(value < top.value) {
                        stack.push(top); // 그 큰값만큼은 보존시키기
                        result = top; // 결과를 top으로 설정
                        break; // 이제 더 이상 스택의 값을 뺄 필요 없음
                    }
                }


                // 스택에 현재 값 집어넣기
                // 값 대신, 인덱스를
                stack.push(new Data(i, value));

                // 저장
                results[i] = result;
            }

            long max = -1;
            for(int i = 0; i < N; i++) {
                Data data = results[i];

                int left;

                if(data == null) {
                    left = 0;
                } else {
                    left = data.index;
                }
                long area = (i - left + 1) * values[i];

                max = Math.max(area, max);
            }

            System.out.println(max);
        }
    }
}
