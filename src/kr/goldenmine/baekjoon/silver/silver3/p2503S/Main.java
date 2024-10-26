package kr.goldenmine.baekjoon.silver.silver3.p2503S;

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
            while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return n;
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

    static class Question {
        int q3;
        int q2;
        int q1;

        int strike;
        int ball;

        Question(String text, int strike, int ball) {
            q3 = text.charAt(0) - '0';
            q2 = text.charAt(1) - '0';
            q1 = text.charAt(2) - '0';
            this.strike = strike;
            this.ball = ball;
        }
    }

    static boolean check(int digit3, int digit2, int digit1, Question question) {
        int strikes = 0;
        if(digit3 == question.q3) strikes++;
        if(digit2 == question.q2) strikes++;
        if(digit1 == question.q1) strikes++;

        int balls = 0;
        if(digit3 == question.q2 || digit3 == question.q1) balls++;
        if(digit2 == question.q3 || digit2 == question.q1) balls++;
        if(digit1 == question.q3 || digit1 == question.q2) balls++;


        return strikes == question.strike && balls == question.ball;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        Question[] questions = new Question[N];

        for(int i = 0; i < N; i++) {
            questions[i] = new Question(scan.nextString(), scan.nextInt(), scan.nextInt());
        }

        int c = 0;
        for(int digit3 = 1; digit3 <= 9; digit3++) {
            for(int digit2 = 1; digit2 <= 9; digit2++) {
                for(int digit1 = 1; digit1 <= 9; digit1++) {
                    if(digit3 == digit2 || digit2 == digit1 || digit1 == digit3) continue;

                    boolean check = true;
                    for(Question q : questions) {
                        check &= check(digit3, digit2, digit1, q);

//                        if(check(digit3, digit2, digit1, q)) {
//                            System.out.println(digit3 + ", " + digit2 + ", " + digit1);
//                        }
                    }

                    if(check) {
                        c++;
                    }
                }
            }
        }
        System.out.println(c);
    }
}
