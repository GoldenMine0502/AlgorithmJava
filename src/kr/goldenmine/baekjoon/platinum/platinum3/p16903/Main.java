package kr.goldenmine.baekjoon.platinum.platinum3.p16903;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char)c;
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

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32);
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -12345; }
            if (c == 45) { c = read(); isMinus = true; }
            else if (c == 46) { c = read(); }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c){
            return (64 < c && c < 91) || (96 < c && c < 123);
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

    static class Trie {
        int count = 0;
        Trie[] sub = new Trie[2]; // zero or one

        void add(int v, int d) {
            int bit = (v & (1 << d)) >> d;
//            System.out.println(v + ": " + bit);

            count++;

            if(d > 0) {
                if(sub[bit] == null) {
                    sub[bit] = new Trie();
                }
                sub[bit].add(v, d - 1);
            }
        }

        void remove(int v, int d) {
            int bit = (v & (1 << d)) >> d;

            count--;
            if(d > 0) {
                sub[bit].remove(v, d - 1);
            }
        }

        int xor(int v, int d) {
            int bit = (v & (1 << d)) >> d;

            /*
            xor은 달라야 1이다.
            bit가 0이면, 1일 때 갈 수 있는 방향이 있는지 체크 ->
            bit가 1이면, 0일 때 갈 수 있는 방향이 있는지 체크
             */
            int res = 0;
            if(bit == 0) {
                if(sub[1] != null && sub[1].count > 0) {
                    res = 1 << d; // 현재 비트를 1로 만들 수 있음
                    res |= sub[1].xor(v, d - 1);
                } else if(sub[0] != null && sub[0].count > 0){
                    res = 0; // 현재 비트를 1로 못만듦
                    res |= sub[0].xor(v, d - 1); // 하위 비트에 값이 있길 기대하자.
                }
            } else /*if(bit == 1)*/ {
                if(sub[0] != null && sub[0].count > 0) {
                    res = 1 << d; // 현재 비트를 1로 만들 수 있음
                    res |= sub[0].xor(v, d - 1);
                } else if(sub[1] != null && sub[1].count > 0) {
                    res = 0; // 현재 비트를 1로 못만듦
                    res |= sub[1].xor(v, d - 1); // 하위 비트에 값이 있길 기대하자.
                }
            }
            System.out.println(v + ", " + d + ", " + res + ", " + bit);
            return res;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int T = scan.nextInt();

        Trie trie = new Trie();

        while(T-- > 0) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            if(a == 1) {
                trie.add(b, 31);
            } else if(a == 2) {
                trie.remove(b, 31);
            } else {
                Trie sub = trie;
                for(int d = 31; d >= 0; d--) {
                    int bit = (b & (1 << d)) >> d;
                    if(bit == 0) {
                        sub = sub.sub[1];
                    }
                }
                int res = trie.xor(b, 31);
                System.out.println(res);
            }
        }
    }
}
