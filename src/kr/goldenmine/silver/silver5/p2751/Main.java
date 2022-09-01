package kr.goldenmine.silver.silver5.p2751;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // + StringTokenizer

        int N = Integer.parseInt(reader.readLine());

        // -1000000~1000000
        int[] arr = new int[2000001];
        for(int i = 0; i < N; i++) {
            int value = Integer.parseInt(reader.readLine());
            arr[value + 1000000]++;
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < 2000001; i++) {
            int count = arr[i];
            for(int j = 0; j < count; j++) {
                writer.write(String.valueOf(i - 1000000));
                writer.newLine();
            }
        }
        writer.flush();
    }
}
