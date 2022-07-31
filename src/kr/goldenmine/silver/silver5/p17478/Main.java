package kr.goldenmine.silver.silver5.p17478;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    static BufferedWriter output = new BufferedWriter(
            new OutputStreamWriter(System.out));

    public static void indent(int count) throws IOException {
        for(int i = 0; i < count; i++) {
            output.write("____");
        }
    }

    public static void count(int N, int count) throws IOException {
        indent(count);
        output.write("\"재귀함수가 뭔가요?\"");
        output.newLine();

        if(count >= N) {
            indent(count);
            output.write("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
            output.newLine();
        } else {
            indent(count);
            output.write("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
            output.newLine();

            indent(count);
            output.write("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
            output.newLine();

            indent(count);
            output.write("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
            output.newLine();

            count(N, count + 1);
        }

        indent(count);
        output.write("라고 답변하였지.");
        output.newLine();
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        output.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        output.newLine();

        count(N, 0);

        output.flush();
    }
}
