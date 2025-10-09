package kr.goldenmine.inu;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class BrainrotKill3 {
    static class Reader {
        final int SIZE = 1 << 14;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
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

    // 기본적으로 BufferedWriter에는 모든 메소드에 synchronized가 붙어있으므로 최대한 synchronized를 빼주도록 작동
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

//        System.out.println(ch1.name + ", " + ch2.name + ", " + ch1Wins + ", " + ch2Wins);

        return ch2Wins - ch1Wins;
    }


    // nodes: size = n, nodes.get(u) = u -> v들의 리스트 (0..n-1)
    public static boolean has3CycleDirected(List<List<Integer>> nodes) {
        int n = nodes.size();
        BitSet[] out = new BitSet[n];
        BitSet[] in  = new BitSet[n];
        for (int i = 0; i < n; i++) {
            out[i] = new BitSet(n);
            in[i]  = new BitSet(n);
        }

        // 인접 비트셋 구성 (자기 루프는 무시)
        for (int u = 0; u < n; u++) {
            for (int v : nodes.get(u)) {
                if (u == v) continue;
                out[u].set(v);
                in[v].set(u);
            }
        }

        // 각 u에 대해: twoHop[u] = ⋃_{v∈out[u]} out[v]
        for (int u = 0; u < n; u++) {
            if (out[u].isEmpty() || in[u].isEmpty()) continue;

            BitSet twoHop = new BitSet(n);
            for (int v = out[u].nextSetBit(0); v >= 0; v = out[u].nextSetBit(v + 1)) {
                twoHop.or(out[v]);
            }
            // 2-사이클(u<->v) 제거: w == u 금지
            twoHop.clear(u);

            // 조건: ∃ w s.t. (u→→w) && (w→u)
            BitSet candW = (BitSet) twoHop.clone();
            candW.and(in[u]);
            if (!candW.isEmpty()) return true;
        }
        return false;
    }


    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        Brainrot[] chars = new Brainrot[N];
        for (int i = 0; i < N; i++) {
            chars[i] = new Brainrot(scan.nextString(), scan.nextInt(), scan.nextInt(), scan.nextInt());
        }
        int[] indegree = new int[N];

        List<List<Integer>> nodes = new ArrayList<>();

        for (int i = 0; i <= N; i++)
            nodes.add(new ArrayList<>());

        // 브루트 포스를 이용하여 각 캐릭터간 승패 유무를 구해야 한다.
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                Brainrot ch1 = chars[i];
                Brainrot ch2 = chars[j];

                int res = getWinner(ch1, ch2);
                if (res < 0) { // ch1 wins
                    nodes.get(i).add(j); // i가 j를 이김.
                    indegree[j]++;
                } else if (res > 0) { // ch2 wins
                    nodes.get(j).add(i); // j가 i를 이김.
                    indegree[i]++;
                }
            }
        }
        System.out.println(nodes);
        System.out.println(Arrays.toString(indegree));

        Writer w = new Writer();

        if(has3CycleDirected(nodes)) {
            w.writeString("Paradoxe Absurdo");
        } else {
            List<String> winners = new ArrayList<>();

            // indegree가 0인 노드 담기
            for (int i = 0; i < N; i++) {
                if (indegree[i] == 0) {
                    winners.add(chars[i].name);
                }
            }

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
        }

        w.close();
    }
}
