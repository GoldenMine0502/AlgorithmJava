package kr.goldenmine.platinum.platinum4.p14868_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static int find(int x) {
        if (p[x] == x) return x;
        return find(p[x]);
    }

    public static boolean union(int x1, int x2) {
        x1 = find(x1);
        x2 = find(x2);
        if (x1 == x2) return false;
        else {
            if (rank[x1] > rank[x2]) {
                p[x2] = x1;
            } else if (rank[x1] < rank[x2]) {
                p[x1] = x2;
            } else {
                rank[x1]++;
                p[x2] = x1;
            }
        }
        return true;
    }

    public static int p[], rank[];
    public static int map[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        p = new int[k + 1];
        rank = new int[k + 1];
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        ArrayDeque<int[]> ddq = new ArrayDeque<>();
        int dir[][] = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        map = new int[n][n];
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = i;
            p[i] = i;
            dq.add(new int[]{y, x});
        }

        //System.out.println(Arrays.toString(p));

        int year = 0;
        while (true) {
            while (!dq.isEmpty()) {
                int now[] = dq.poll();
                ddq.add(new int[]{now[0], now[1]});
                int y = now[0], x = now[1];

                for (int i = 0; i < 4; i++) {
                    int ny = y + dir[i][0];
                    int nx = x + dir[i][1];
                    if (ny >= 0 && nx >= 0 && nx < n && ny < n) {
                        if (map[ny][nx] != 0) {
                            if (map[ny][nx] != map[y][x]) {
                                if (union(map[ny][nx], map[y][x])) {
                                    k--;
                                }
                            }
                        }
                    }
                }
            }
            if (k == 1) {
                System.out.println(year);
                break;
            }

            while (!ddq.isEmpty()) {
                int now[] = ddq.poll();
                int y = now[0], x = now[1];
                for (int i = 0; i < 4; i++) {
                    int ny = y + dir[i][0];
                    int nx = x + dir[i][1];
                    if (ny >= 0 && nx >= 0 && nx < n && ny < n) {
                        if (map[ny][nx] == 0) {
                            map[ny][nx] = map[y][x]; //그냥 문명 전파
                            dq.add(new int[]{ny, nx});
                        } else if (map[ny][nx] != 0 && map[ny][nx] != map[y][x]) {
                            if (union(map[ny][nx], map[y][x])) {
                                k--;
                            }
                        }
                    }
                }
            }
            year++;
        }
    }
}
