package kr.goldenmine.baekjoon.gold.gold1.p17143F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static final int UP = 4;
    static final int DOWN = 3;
    static final int RIGHT = 2;
    static final int LEFT = 1;

    static class Shark {
        char alphabet;
        int x;
        int y;
        int speed;
        int direction;
        int size;

        int myCacheForRemovingShark;

        public Shark(char alphabet, int x, int y, int speed, int direction, int size) {
            this.alphabet = alphabet;
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        public void reverse() {
            if (direction == UP) {
                direction = DOWN;
            } else if (direction == DOWN) {
                direction = UP;
            } else if (direction == RIGHT) {
                direction = LEFT;
            } else {
                direction = RIGHT;
            }
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "alphabet=" + alphabet +
                    ", x=" + x +
                    ", y=" + y +
                    ", speed=" + speed +
                    ", direction=" + direction +
                    ", size=" + size +
                    ", myCacheForRemovingShark=" + myCacheForRemovingShark +
                    '}';
        }
    }

    /* 2, 2에서 만나게 해보자
    4 4 3
    3 2 1 1 3
    2 3 1 4 2
    1 2 1 2 1

     */

    public static void moveSharks(int X, int Y, Shark[][] arr, List<Shark> sharks) {


        for (int i = 0; i < sharks.size(); i++) {
            Shark shark = sharks.get(i);

            System.out.println(shark);

            arr[shark.y][shark.x] = null;

            int remainingSpeed = shark.speed;

            if (shark.direction == UP || shark.direction == DOWN) {
                while (true) {
                    if (shark.direction == UP) {
                        if (shark.y - remainingSpeed >= 0) {
                            shark.y -= remainingSpeed;
                            break;
                        } else {
                            remainingSpeed -= shark.y;
                            shark.y = 0;
                            shark.direction = DOWN;
                        }
                    } else {
                        if (shark.y + remainingSpeed < Y) {
                            shark.y += remainingSpeed;
                            break;
                        } else {
                            remainingSpeed -= Y - shark.y - 1;
                            shark.y = Y - 1;
                            shark.direction = UP;
                        }
                    }
                }
            } else if (shark.direction == LEFT || shark.direction == RIGHT){
                while (true) {
                    if (shark.direction == LEFT) {
                        if (shark.x - remainingSpeed >= 0) {
                            shark.x -= remainingSpeed;
                            break;
                        } else {
                            remainingSpeed -= shark.x;
                            shark.x = 0;
                            shark.direction = RIGHT;
                        }
                    } else {
                        if (shark.x + remainingSpeed < X) {
                            shark.x += remainingSpeed;
                            break;
                        } else {
                            remainingSpeed -= X - shark.x - 1;
                            shark.x = X - 1;
                            shark.direction = LEFT;
                        }
                    }
                }
            }


            if(arr[shark.y][shark.x] != null) {
                if(arr[shark.y][shark.x].size < shark.size) {
                    // 상어 한마리가 잡아먹힘
                    int c = 0;
                    for(Shark shark1 : sharks) {
                        if(shark1.x == shark.x && shark1.y == shark.y) {
                            if(shark1 != shark) { // 다른걸 골라야함
                                break;
                            }
                        }
                        c++;
                    }
                    System.out.println("killed: " + arr[shark.y][shark.x] + " by " + shark);

                    sharks.remove(c);
                    arr[shark.y][shark.x] = shark;
                    i--;
                } else { // 작다고? 그럼 니가 잡아먹힘 ㅋㅋㅋ
                    int c = 0;
                    for(Shark shark1 : sharks) {
                        if(shark1.x == shark.x && shark1.y == shark.y) {
                            if(shark1 == shark) { // 같은걸 골라야함
                                break;
                            }
                        }
                        c++;
                    }

                    sharks.remove(c);
                    i--;
                    System.out.println("killed self: " + shark + " by " + arr[shark.y][shark.x]);
                }
            } else {
                System.out.println("insert: " + shark);
                arr[shark.y][shark.x] = shark;
            }

            System.out.println(sharks.size());
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int X = scan.nextInt();
        int Y = scan.nextInt();
        int M = scan.nextInt();

        Shark[][] arr = new Shark[Y][X];

        List<Shark> sharks = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            int x = scan.nextInt() - 1;
            int y = scan.nextInt() - 1;
            int speed = scan.nextInt();
            int direction = scan.nextInt();
            int size = scan.nextInt();

            Shark shark = new Shark((char) (i + 'A'), x, y, speed, direction, size);
            shark.myCacheForRemovingShark = i;
            sharks.add(shark);

            arr[shark.y][shark.x] = shark;
        }

        System.out.println();
        for(int x = 0; x < X; x++) {
            for(int y = 0; y < Y; y++) {
                System.out.print((arr[y][x] != null ? arr[y][x].alphabet : '0') + " ");
            }
            System.out.println();
        }

        int total = 0;

        for(int i = 0; i < Y; i++) {
            Shark shark = null;
            int c = 0;
            int index = -1;
            for(Shark shark1 : sharks) {
                if(shark1.y == i) {
                    if(shark == null) {
                        shark = shark1;
                        index = c;
                    } else if(shark.x > shark1.x) {
                        shark = shark1;
                        index = c;
                    }
                }
                c++;
            }

            System.out.println("fished: " + shark);

            if(shark != null) {
                total += shark.size;
                arr[shark.y][shark.x] = null;
                sharks.remove(index);
            }

            moveSharks(X, Y, arr, sharks);

            for(int x = 0; x < X; x++) {
                for(int y = 0; y < Y; y++) {
                    System.out.print((arr[y][x] != null ? arr[y][x].alphabet : '0') + " ");
                }
                System.out.println();
            }

            System.out.println(i + ": " + total);
        }

        System.out.println(total);
    }
}
