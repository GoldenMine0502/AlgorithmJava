package kr.goldenmine.baekjoon.silver.silver3.p10157;

public class Main {
    static class Reader {
        final int SIZE = 1 << 5;
        byte[] buffer = new byte[SIZE];
        int index, size;


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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int C = scan.nextInt();
        int R = scan.nextInt();

        int N = scan.nextInt();

        int direction = 0; // 0 = 위, 1 = 오른쪽, 2 = 아래쪽, 3 = 왼쪽
        int left = 2;
        int right = C;
        int top = R;
        int bottom = 1;

        int x = 1;
        int y = 0;

        if(R * C < N) {
            System.out.print(0);
            return;
        }

        for(int i = 1; i <= N; i++) {
            switch(direction) {
                case 0:
                    if(y < top) {
                        y++;
                    } else {
                        direction = 1;
                        top--;
                        i--;
                    }
                    break;
                case 1:
                    if(x < right) {
                        x++;
                    } else {
                        direction = 2;
                        right--;
                        i--;
                    }
                    break;
                case 2:
                    if(y > bottom) {
                        y--;
                    } else {
                        direction = 3;
                        bottom++;
                        i--;
                    }
                    break;
                case 3:
                    if(x > left) {
                        x--;
                    } else {
                        direction = 0;
                        left++;
                        i--;
                    }
                    break;
            }
//            System.out.println(i + ": " + x + " " + y + " / " + top + ", " + bottom + ", " + left + ", " + right);
        }

        System.out.print(x + " " + y);
    }
}
