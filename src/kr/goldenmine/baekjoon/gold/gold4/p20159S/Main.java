package kr.goldenmine.baekjoon.gold.gold4.p20159S;

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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        // 10000 * 100000 -> int범위 커버 가능
        int[] arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = scan.nextInt();
        }

        int[] sumsoddeven = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            sumsoddeven[i] = (i >= 2 ? sumsoddeven[i - 2] : 0) + arr[i];
        }

        // 6
        // 3 2 5 2 1 3
        // 1 2 4 5 6 3
        // 4
        // 5 10 1 3
        // 이때의 i는 몇번째 카드를 밑장을 뺄것인가
        int max = sumsoddeven[N - 1]; // 밑장을 안뻈을 때 값
        // 내 카드를 밑장 빼기
        for(int i = 1; i <= N; i += 2) {
            // i번째까지 홀수번째 합 + 밑장뺀 값 + 뒤의 짝수번째 합
            int front = (i >= 2 ? sumsoddeven[i - 2] : 0);
            int last = arr[N];
            int back = sumsoddeven[N - 2] - sumsoddeven[i - 1];
            int current = front + last + back;

            max = Math.max(max, current);
        }

        // 상대 카드를 밑장 빼기
        for(int i = 2; i <= N; i += 2) {
            int front = sumsoddeven[i - 1];
//            int last = arr[N]; last값을 상대에게 줬으니 생략
            int back = sumsoddeven[N - 2] - sumsoddeven[i - 2];
            int current = front + back;

            max = Math.max(max, current);
        }
        System.out.println(max);
    }
}
