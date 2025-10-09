package kr.goldenmine.inu;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class BrainrotChecker {

    static class Reader {
        final int SIZE = 1 << 14;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) > 32);
            return sb.toString();
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

     static class Writer extends BufferedWriter {
        char[] buf;
        int nChars;
        int index;

        char[] bufForWriteNumber = new char[24];

        Writer() {
            this(1 << 10);
        }

        Writer(int size) {
            super(new OutputStreamWriter(System.out), size);
            buf = new char[size];
            nChars = size;
        }

        public void writeInt(int n) throws IOException {
            boolean minus = n < 0;
            if(minus) n = ~n + 1;

            int d;
            for(d = 12 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            if(minus) {
                bufForWriteNumber[--d] = '-';
            }

            for(; d < 12; d++) {
                write(bufForWriteNumber[d]);
            }
        }

        public void writeLong(long n) throws IOException {
            boolean minus = n < 0;
            if(minus) n = ~n + 1;

            int d;
            for(d = 22 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            if(minus) {
                bufForWriteNumber[--d] = '-';
            }

            for(; d < 22; d++) {
                write(bufForWriteNumber[d]);
            }
        }

        public void writeString(String s) throws IOException {
            for(int i = 0; i < s.length(); i++) {
                write(s.charAt(i));
            }
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            for(int i = off; i < off + len; i++) {
                write(cbuf[i]);
            }
        }

        public void write(int c) throws IOException {
            if (index >= nChars) flush();
            buf[index++] = (char) c;
        }

        public void newLine() throws IOException {
            write('\n');
        }

        @Override
        public void flush() throws IOException {
            super.write(buf, 0, index);
            index = 0;
            super.flush(); // TODO 여기에 synchronized가 달려있어서 없애야한다
        }

        @Override
        public void close() throws IOException {
            flush();
            super.close();
        }
    }

    public static boolean hasCleanCycleLen5(List<List<Integer>> nodes, int N) {
        boolean[][] adj = new boolean[N][N];
        for (int u = 0; u < N; u++) {
            for (int v : nodes.get(u)) {
                if (v >= 0 && v < N) adj[u][v] = true;
            }
        }

        int[] comb = new int[5];
        return combineAndCheck(0, 0, N, comb, adj);
    }

    private static boolean combineAndCheck(int idx, int start, int N, int[] comb, boolean[][] adj) {
        if (idx == 5) {
            int[] verts = Arrays.copyOf(comb, 5);
            if (existsDirectedCycleLenKInSet(verts, 5, adj)) {
                if (!hasDirectedCycleLen3InSet(verts, adj) && !hasDirectedCycleLen4InSet(verts, adj)) {
                    return true;
                }
            }
            return false;
        }
        for (int v = start; v < N; v++) {
            comb[idx] = v;
            if (combineAndCheck(idx + 1, v + 1, N, comb, adj)) return true;
        }
        return false;
    }

    private static boolean existsDirectedCycleLenKInSet(int[] verts, int K, boolean[][] adj) {
        int m = verts.length;
        boolean[] used = new boolean[m];
        int[] pathIdx = new int[K];

        for (int start = 0; start < m; start++) {
            Arrays.fill(used, false);
            pathIdx[0] = start;
            used[start] = true;
            if (dfsCycle(verts, adj, used, pathIdx, 1, K)) return true;
        }
        return false;
    }

    private static boolean dfsCycle(int[] verts, boolean[][] adj, boolean[] used,
                                    int[] pathIdx, int depth, int K) {
        if (depth == K) {
            int a = verts[pathIdx[K - 1]];
            int b = verts[pathIdx[0]];
            return adj[a][b];
        }
        int prev = verts[pathIdx[depth - 1]];
        for (int i = 0; i < verts.length; i++) {
            if (!used[i]) {
                int cur = verts[i];
                if (adj[prev][cur]) {
                    used[i] = true;
                    pathIdx[depth] = i;
                    if (dfsCycle(verts, adj, used, pathIdx, depth + 1, K)) return true;
                    used[i] = false;
                }
            }
        }
        return false;
    }

    private static boolean hasDirectedCycleLen3InSet(int[] verts, boolean[][] adj) {
        int m = verts.length;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                for (int k = j + 1; k < m; k++) {
                    int a = verts[i], b = verts[j], c = verts[k];
                    if ((adj[a][b] && adj[b][c] && adj[c][a]) ||
                            (adj[a][c] && adj[c][b] && adj[b][a])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean hasDirectedCycleLen4InSet(int[] verts, boolean[][] adj) {
        int m = verts.length;

        int[] sub = new int[4];
        for (int a = 0; a < m; a++) {
            for (int b = a + 1; b < m; b++) {
                for (int c = b + 1; c < m; c++) {
                    for (int d = c + 1; d < m; d++) {
                        sub[0] = verts[a];
                        sub[1] = verts[b];
                        sub[2] = verts[c];
                        sub[3] = verts[d];
                        if (existsCycleLen4InFour(sub, adj)) return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean existsCycleLen4InFour(int[] four, boolean[][] adj) {
        boolean[] used = new boolean[4];
        int[] order = new int[4];
        for (int s = 0; s < 4; s++) {
            Arrays.fill(used, false);
            order[0] = s;
            used[s] = true;
            if (dfsCycle4(four, adj, used, order, 1)) return true;
        }
        return false;
    }

    private static boolean dfsCycle4(int[] four, boolean[][] adj, boolean[] used, int[] order, int depth) {
        if (depth == 4) {
            int a = four[order[3]];
            int b = four[order[0]];
            return adj[a][b];
        }
        int prev = four[order[depth - 1]];
        for (int i = 0; i < 4; i++) {
            if (!used[i]) {
                int cur = four[i];
                if (adj[prev][cur]) {
                    used[i] = true;
                    order[depth] = i;
                    if (dfsCycle4(four, adj, used, order, depth + 1)) return true;
                    used[i] = false;
                }
            }
        }
        return false;
    }


    static class Brainrot {
        String name;
        int P;
        int A;
        int S;

        Brainrot(String name, int P, int A, int S) {
            this.name = name;
            this.P = P;
            this.A = A;
            this.S = S;
        }
    }

    static int getWinner(Brainrot ch1, Brainrot ch2) {
        int ch1Wins = 0;
        int ch2Wins = 0;

        if(ch1.P >= ch2.P) ch1Wins++;
        if(ch1.P <= ch2.P) ch2Wins++;
        if(ch1.A >= ch2.A) ch1Wins++;
        if(ch1.A <= ch2.A) ch2Wins++;
        if(ch1.S >= ch2.S) ch1Wins++;
        if(ch1.S <= ch2.S) ch2Wins++;

        return ch2Wins - ch1Wins;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        List<List<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < N; i++) nodes.add(new ArrayList<>());

        Brainrot[] chars = new Brainrot[N];
        for(int i = 0; i < N; i++) {
            chars[i] = new Brainrot(scan.nextString(), scan.nextInt(), scan.nextInt(), scan.nextInt());
        }
        int[] indegree = new int[N];

        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                Brainrot ch1 = chars[i];
                Brainrot ch2 = chars[j];

                int res = getWinner(ch1, ch2);
                if(res < 0) { // ch1 wins
                    nodes.get(i).add(j);
                    indegree[j]++;
                } else if(res > 0) { // ch2 wins
                    nodes.get(j).add(i);
                    indegree[i]++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        List<String> winners = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                winners.add(chars[i].name);
            }
        }

        int count = 0;
        while(!q.isEmpty()) {

            // 사이클 찾기
            int node = q.poll();
            count++;

            for (Integer j : nodes.get(node)) {
                indegree[j]--;

                if (indegree[j] == 0) {
                    q.offer(j);
                }
            }
        }

        Writer w = new Writer();
        if(count == N) {
            winners.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            for(int i = 0; i < winners.size(); i++) {
                w.writeString(winners.get(i));
                w.write('\n');
            }
        } else {
            w.writeString("Paradoxe Absurdo");
        }

        boolean ok = hasCleanCycleLen5(nodes, N);
        assert !ok;

        w.close();
    }
}
