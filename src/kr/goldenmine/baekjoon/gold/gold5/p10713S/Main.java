package kr.goldenmine.baekjoon.gold.gold5.p10713S;

public class Main {
    static class Reader {
        final int SIZE = 1 << 15;
        byte[] buffer = new byte[SIZE];
        int index, size;

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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[] routes = new int[M + 1];
        for(int i = 1; i <= M; i++) {
            routes[i] = scan.nextInt();
        }

        // A = 그냥 이동
        // B = 티켓으로 이동
        // C = IC 카드 구매
        int[] move = new int[N];
        int[] moveWithTicket = new int[N];
        int[] purchaseCard = new int[N];

        for(int i = 1; i < N; i++) {
            move[i] = scan.nextInt();
            moveWithTicket[i] = scan.nextInt();
            purchaseCard[i] = scan.nextInt();
        }

        int[] sum = new int[N + 1];

        for(int i = 1; i < M; i++) {
            int left = Math.min(routes[i], routes[i + 1]);
            int right = Math.max(routes[i], routes[i + 1]);

            sum[left]++;
            sum[right]--;
        }

        // 총 철도 사용 횟수
        // 예제 1에서 [1, 3, 1] 나와야함
        long[] total = new long[N];
        long current = 0;
        for(int i = 1; i < N; i++) {
            current += sum[i];
            total[i] = current;
        }

        long ans = 0;
        for(int i = 1; i < N; i++) {
            // 티켓을 구매했을 때 합
            long with = total[i] * moveWithTicket[i] + purchaseCard[i];
            // 티켓 없이 합
            long without = total[i] * move[i];

            ans += Math.min(with, without);
        }

        System.out.println(ans);
    }
}
