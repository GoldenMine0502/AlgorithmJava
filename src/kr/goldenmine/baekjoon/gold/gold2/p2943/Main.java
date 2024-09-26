package kr.goldenmine.baekjoon.gold.gold2.p2943;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -1;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            }
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

    static class Coin {
        int price;
        int count;

        Coin(int price, int count) {
            this.price = price;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int T = 3;

        while (T-- > 0) {
            int N = scan.nextInt();
            Coin[] coins = new Coin[N + 1];
            int SUM = 0;
            for (int i = 1; i <= N; i++) {
                coins[i] = new Coin(scan.nextInt(), scan.nextInt());
                SUM += coins[i].price * coins[i].count;
            }

            if (SUM % 2 == 1) {
                System.out.println(0);
                continue;
            }

            int[] dp = new int[SUM + 1];
            dp[0] = 1;

            for (int i = 1; i <= N; i++) {
                Coin coin = coins[i];

                for (int m = SUM / 2; m >= 1; m--) {
                    if (dp[m] == 1) continue;

                    for (int c = 1; c <= coin.count; c++) {
                        int previous = m - c * coin.price;
                        if (previous < 0) break;

                        dp[m] |= dp[previous];
                    }
                }
            }
//            System.out.println(Arrays.toString(dp));
            System.out.println(dp[SUM / 2]);
        }
    }
    /*
2
5 1
15 1
3
10 2
5 1
1 5
3
1 1
2 1
3 1
     */
}
