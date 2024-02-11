package kr.goldenmine.baekjoon.gold.gold5.p2251S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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

    static class State {
        int A;
        int B;
        int C;

        public State(int A, int B, int C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }

        @Override
        public String toString() {
            return "State{" +
                    "A=" + A +
                    ", B=" + B +
                    ", C=" + C +
                    '}';
        }
    }

    public static boolean checkVisited(State state, boolean[][][] visited) {
        if (!visited[state.A][state.B][state.C]) {
            visited[state.A][state.B][state.C] = true;
            return true;
        }

        return false;
    }

    public static void bfs(int A, int B, int C, boolean[] visitedC, boolean[][][] visited, State start) {
        Queue<State> points = new LinkedList<>();

        points.add(start);
        visited[start.A][start.B][start.C] = true;

        while (!points.isEmpty()) {
            State state = points.poll();

            if(state.A == 0)
                visitedC[state.C] = true;
            /*
            여섯 가지 경우가 있을 텐데,
            C -> A,
            C -> B,
            B -> A,
            ...
            BFS로 진행하면 애초에 최소횟수로 방문할테니 걱정 없이 해당 state에 대한 방문 체크만 잘 해주면 된다.
            아래 노가다를 줄일 방법에 대해 생각해 봐야 한다.
             */
            /* C를 비우는 경우 */
            if(state.C > 0) {
                if(state.A < A) { // A에 부을 수 있는 상태
                    int drain = Math.min(state.C, A - state.A); // 가능한 한 많이 붓기. 최대한 많이 담을 수 있는 양 체크
                    State next = new State(state.A + drain, state.B, state.C - drain);

                    // 첫 조건: 한 물통이 빌 때, 두번째 조건: 다른 한 물통이 찰 때
                    if(next.A == A || next.C == 0) {
                        if (checkVisited(next, visited)) {
                            points.add(next);
                        }
                    }
                }

                if(state.B < B) { // B에 부을 수 있는 상태
                    int drain = Math.min(state.C, B - state.B); // 가능한 한 많이 붓기. 최대한 많이 담을 수 있는 양 체크
                    State next = new State(state.A, state.B + drain, state.C - drain);

                    // 첫 조건: 한 물통이 빌 때, 두번째 조건: 다른 한 물통이 찰 때
                    if(next.B == B || next.C == 0) {
                        if (checkVisited(next, visited)) {
                            points.add(next);
                        }
                    }
                }
            }

            /* B를 비우는 경우 */
            if(state.B > 0) {
                if(state.A < A) { // A에 부을 수 있는 상태
                    int drain = Math.min(state.B, A - state.A); // 가능한 한 많이 붓기. 최대한 많이 담을 수 있는 양 체크
                    State next = new State(state.A + drain, state.B - drain, state.C);

                    // 첫 조건: 한 물통이 빌 때, 두번째 조건: 다른 한 물통이 찰 때
                    if(next.A == A || next.B == 0) {
                        if (checkVisited(next, visited)) {
                            points.add(next);
                        }
                    }
                }

                if(state.C < C) { // C에 부을 수 있는 상태
                    int drain = Math.min(state.B, C - state.C); // 가능한 한 많이 붓기. 최대한 많이 담을 수 있는 양 체크
                    State next = new State(state.A, state.B - drain, state.C + drain);

                    // 첫 조건: 한 물통이 빌 때, 두번째 조건: 다른 한 물통이 찰 때
                    if(next.C == C || next.B == 0) {
                        if (checkVisited(next, visited)) {
                            points.add(next);
                        }
                    }
                }
            }

            /* A를 비우는 경우 */
            if(state.A > 0) {
                if (state.C < C) { // C에 부을 수 있는 상태
                    int drain = Math.min(state.A, C - state.C); // 가능한 한 많이 붓기. 최대한 많이 담을 수 있는 양 체크
                    State next = new State(state.A - drain, state.B, state.C + drain);

                    // 첫 조건: 한 물통이 빌 때, 두번째 조건: 다른 한 물통이 찰 때
                    if (next.C == C || next.A == 0) {
                        if (checkVisited(next, visited)) {
                            points.add(next);
                        }
                    }
                }

                if (state.B < B) { // B에 부을 수 있는 상태
                    int drain = Math.min(state.A, B - state.B); // 가능한 한 많이 붓기. 최대한 많이 담을 수 있는 양 체크
                    State next = new State(state.A - drain, state.B + drain, state.C);

                    // 첫 조건: 한 물통이 빌 때, 두번째 조건: 다른 한 물통이 찰 때
                    if (next.B == B || next.A == 0) {
                        if (checkVisited(next, visited)) {
                            points.add(next);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int A = scan.nextInt();
        int B = scan.nextInt();
        int C = scan.nextInt();

        boolean[][][] visited = new boolean[201][201][201];

        boolean[] visitedC = new boolean[201];

        bfs(A, B, C, visitedC, visited, new State(0, 0, C));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 200; i++) {
            if (visitedC[i]) {
                sb.append(i);
                sb.append(" ");
            }
        }

        System.out.println(sb);
    }
}
