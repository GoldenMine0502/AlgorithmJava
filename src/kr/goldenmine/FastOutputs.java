package kr.goldenmine;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FastOutputs {
    public static void main(String[] args) throws IOException {
        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        // 예시 출력
        for(int i = 0; i < 1000; i++) {
            output.write("a");
            output.write(" ");
        }

        output.flush();
    }
}
