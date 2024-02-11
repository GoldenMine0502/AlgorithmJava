package kr.goldenmine.baekjoon.gold.gold4.p7662A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

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

//    public static void main(String[] args) {
//        TreeMap<Integer, Integer> map = new TreeMap<>();
//
////        map.put(1, 4);
//
//        int value = map.put(1, 3);
//
//        System.out.println(value);
//    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int T = scan.nextInt();

        while(T-- > 0) {
            int N = scan.nextInt();

            TreeMap<Integer, Integer> map = new TreeMap<>();

            for(int i = 0; i < N; i++) {
                String command = scan.next();
                int value = scan.nextInt();

//                System.out.println(command + ", " + value + ", " + map);

                if(command.equals("I")) { // insert
                    // 숫자를 증가시키는 경우이므로 같은 값이 트리에 들어갈 일은 없다
                    map.put(value, map.getOrDefault(value, 0) + 1);
                } else { // delete
                    if(map.size() == 0) continue;

//                  map.firstKey(); // 최솟값
//                  map.lastKey(); // 최댓값

                    int removingValue;
                    if (value == 1) { // 1은 최댓값 제거
                        removingValue = map.lastKey();
                    } else {
                        removingValue = map.firstKey();
                    }

//                    System.out.println(removingValue + ", " + map.get(removingValue));

                    // result는 map에 put하기 전 값을 리턴한다. 좋은데?
                    int result = map.put(removingValue, map.get(removingValue) - 1);

                    if(result == 1) { // put하기 전 값이 1이라는건 지금 0이라는 뜻이지
                        map.remove(removingValue);
                    }
                }
            }

            if(map.size() > 0) {
                System.out.println(map.lastKey() + " " + map.firstKey());
            } else {
                System.out.println("EMPTY");
            }
        }
    }
}
