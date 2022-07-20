package kr.goldenmine.gold.gold4.p17298S;

import java.io.*;
import java.util.*;

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

    public static void main(String[] args) throws IOException {
        // 값 입력 받기
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int[] values = new int[N];

        for(int i = 0; i < N; i++) {
            values[i] = scan.nextInt();
        }

        /*
        아이디어
        일단 마지막을 스택에 집어 넣는다.
        그리고 그 "앞 값"을 기준으로, 그 "앞 값"보다 큰 값이 나올 때 까지 pop한다.

        큰 값이 나오면 해당 값은 NGE이며 결과값을 결과 배열에 넣어주고 현재 값을 스택에 집어넣는다.
        이로서 불필요한 작은 값을 스택에서 뺄 수 있다.!
         */

        // solve
        int[] results = new int[N];
        ArrayDeque<Integer> stack = new ArrayDeque<>(N);

        for(int i = N - 1; i >= 0; i--) {
            int value = values[i];
            int result = -1;



            // 비지 않을 때까지 계속 반복
            while(!stack.isEmpty()) {
                int top = stack.pop();

                // 만약 큰 값을 찾았다면?
                // 일단 여기에서 true라면 해당하는 값보다 가까운 최대의 값일듯
                if(value < top) {
                    stack.push(top); // 그 큰값만큼은 보존시키기
                    result = top; // 결과를 top으로 설정
                    break; // 이제 더 이상 스택의 값을 뺄 필요 없음
                }
            }


            // 스택에 현재 값 집어넣기
            stack.push(value);

            // 저장
            results[i] = result;
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
