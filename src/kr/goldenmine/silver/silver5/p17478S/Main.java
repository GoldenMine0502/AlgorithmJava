package kr.goldenmine.silver.silver5.p17478S;

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
        output.write("\"����Լ��� ������?\"");
        output.newLine();

        if(count >= N) {
            indent(count);
            output.write("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"");
            output.newLine();
        } else {
            indent(count);
            output.write("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.");
            output.newLine();

            indent(count);
            output.write("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.");
            output.newLine();

            indent(count);
            output.write("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"");
            output.newLine();

            count(N, count + 1);
        }

        indent(count);
        output.write("��� �亯�Ͽ���.");
        output.newLine();
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        output.write("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.");
        output.newLine();

        count(N, 0);

        output.flush();
    }
}
