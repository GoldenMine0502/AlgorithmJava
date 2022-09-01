package kr.goldenmine.bronze.bronze1.p10989;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    private static final int BUFFER_SIZE = 1 << 22;
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
        int[] results = new int[10001];

        int N = nextInt();

        for(int i = 0; i < N; i++) {
            results[nextInt()]++;
        }

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        for(int i = 1; i <= 10000; i++) {
            int count = results[i];
            for(int j = 0; j < count; j++) {
                output.write(String.valueOf(i));
                output.newLine();
            }
        }

        output.flush();
    }
}
