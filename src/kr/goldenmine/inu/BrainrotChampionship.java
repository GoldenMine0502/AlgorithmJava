package kr.goldenmine.inu;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class BrainrotChampionship {
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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        Brainrot[] chars = new Brainrot[N];
        for(int i = 0; i < N; i++) {
            chars[i] = new Brainrot(scan.nextString(), scan.nextInt(), scan.nextInt(), scan.nextInt());
        }
        int[] indegree = new int[N];

        List<List<Integer>> nodes = new ArrayList<>();

        for (int i = 0; i <= N; i++)
            nodes.add(new ArrayList<>());

        // 브루트 포스를 이용하여 각 캐릭터간 승패 유무를 구해야 한다.
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                Brainrot ch1 = chars[i];
                Brainrot ch2 = chars[j];

                int res = getWinner(ch1, ch2);
                if(res < 0) { // ch1 wins
                    nodes.get(i).add(j); // i가 j를 이김.
                    indegree[j]++;
                } else if(res > 0) { // ch2 wins
                    nodes.get(j).add(i); // j가 i를 이김.
                    indegree[i]++;
                }
            }
        }

//        System.out.println(Arrays.toString(indegree));

        // 위상 정렬 실행
        Queue<Integer> q = new LinkedList<>();
        List<String> winners = new ArrayList<>();

        // indegree가 0인 노드 담기
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                winners.add(chars[i].name);
            }
        }

        /**
         * 1. 큐에서 값을 꺼내며 해당 노드가 가리키는 노드의 indegree 를 1 감소
         * 2. 만약 indegree가 0 이 된다면 큐에 넣기
         * 3. 큐가 빌때까지 반복
         */
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
//        System.out.println(count);

        Writer w = new Writer();
        if(count == N) {
            winners.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
//            winners.sort(String::compareTo);

            for(int i = 0; i < winners.size(); i++) {
                w.writeString(winners.get(i));
                w.write('\n');
            }
        } else {
            w.writeString("Paradoxe Absurdo");
        }
        w.close();
    }
}
