package kr.goldenmine.gold.gold5.p2493S;

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
        int value;

        public Data(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        // 값 입력 받기
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int[] values = new int[N];

        for(int i = 0; i < N; i++) {
            values[i] = scan.nextInt();
        }

        /*
        이번엔 오큰수가 아니라 왼큰수 아니냐...?

         */

        // solve
        int[] results = new int[N];
        ArrayDeque<Data> stack = new ArrayDeque<>(N);

        for(int i = 0; i < N; i++) {
            int value = values[i];
            int result = -1;

            // 비지 않을 때까지 계속 반복
            while(!stack.isEmpty()) {
                Data top = stack.pop();

                // 만약 큰 값을 찾았다면?
                // 일단 여기에서 true라면 해당하는 값보다 가까운 최대의 값일듯
                if(value < top.value) {
                    stack.push(top); // 그 큰값만큼은 보존시키기
                    result = top.index; // 결과를 top으로 설정
                    break; // 이제 더 이상 스택의 값을 뺄 필요 없음
                }
            }


            // 스택에 현재 값 집어넣기
            // 값 대신, 인덱스를
            stack.push(new Data(i, value));

            // 저장
            results[i] = result + 1;
        }

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));



        for(int i = 0; i < N; i++) {
            output.write(String.valueOf(results[i]));
            output.write(" ");
        }

        output.flush();
    }
}
