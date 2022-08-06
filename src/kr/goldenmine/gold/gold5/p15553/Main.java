package kr.goldenmine.gold.gold5.p15553;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final int BUFFER_SIZE = 1 << 19;
    private static DataInputStream din = new DataInputStream(System.in);
    private static byte[] buffer = new byte[BUFFER_SIZE];
    private static int bufferPointer = 0, bytesRead = 0;

    public static int nextInt() throws IOException {
        int ret = 0;

        byte c = read();
        while (c <= ' ') {
            c = read();
        }

        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        return ret;
    }

    private static byte read() throws IOException {
        if (bufferPointer == bytesRead) {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
        return buffer[bufferPointer++];
    }

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int K = nextInt();
        int[] diffs = new int[N - 1];

        int previous = nextInt();
        for(int i = 1; i < N; i++) {
            diffs[i - 1] = -previous + (previous = nextInt());
        }

//        Arrays.sort(diffs);
        int left = 0;
        int right = N - 2;
        while(left <= right) {
            int pivot = diffs[right];
            int i = left - 1;
            for(int j = left; j <= right - 1; j++) {
                if(diffs[j] <= pivot){
                    i++;
                    int temp = diffs[i];
                    diffs[i] = diffs[j];
                    diffs[j] = temp;
                }
            }
            int temp = diffs[i + 1];
            diffs[i + 1] = diffs[right];
            diffs[right] = temp;
            int pi = i + 1;
            if(N - K == pi) {
                break;
            }
            if(N - K < pi){
                right = pi - 1;
            } else {
                left = pi + 1;
            }
        }

        int sum = K;
        for(int i = 0; i < N - K; i++) {
            sum += diffs[i];
        }

        System.out.print(sum);
    }
}
