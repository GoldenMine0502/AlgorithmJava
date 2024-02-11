package kr.goldenmine.baekjoon.silver.silver2.p22333S;

import java.io.*;
import java.util.HashSet;

public class Main {
    final private static int BUFFER_SIZE = 1 << 19;
    private static DataInputStream din = new DataInputStream(System.in);
    private static byte[] buffer = new byte[BUFFER_SIZE];
    private static int bufferPointer = 0, bytesRead = 0;
    private static byte[] buf = new byte[111]; // line length

    private static void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0,
                BUFFER_SIZE);
        if (bytesRead == -1)
            buffer[0] = -1;
    }

    private static byte read() throws IOException {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }

    public static void main(String[] args) throws IOException {
        int cnt = 0, c, N = 0, M = 0;

        // N, M 입력 받기
        c = read();
        do {
            N = N * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        c = read();
        do {
            M = M * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        // N 문자열 입력 받기
        HashSet<String> set = new HashSet<>(N);

        for (int i = 0; i < N; i++) {
            while ((c = read()) != '\n') {
                buf[cnt++] = (byte) c;
            }
            set.add(new String(buf, 0, cnt));
            cnt = 0;
        }

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out), 1 << 12); // 7 * 100000

        for (int i = 0; i < M; i++) {
            while ((c = read()) != -1) {
                if (c == ',' || c == '\n') {
                    set.remove(new String(buf, 0, cnt));
                    cnt = 0;

                    if (c == '\n') break;
                } else {
                    buf[cnt++] = (byte) c;
                }
            }
            output.write(String.valueOf(set.size()));
            output.newLine();
        }

        output.flush();
    }
}
