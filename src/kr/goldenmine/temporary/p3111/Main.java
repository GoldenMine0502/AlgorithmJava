package kr.goldenmine.temporary.p3111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

    public static void main(String[] args) throws InterruptedException {
        FastReader scan = new FastReader();

        String A = scan.next();
        String T = scan.next();

        ArrayDeque<Character> deque = new ArrayDeque<>();
        List<Character> buffer = new ArrayList<>();


        for(int i = 0; i < T.length(); i++) { // 우선 deque에 추가하는 부분
            char textOne = T.charAt(i);

            deque.addLast(textOne);
        }

        int find;
        boolean first = true;

        do {
            find = 0;
            if(first) {
                while(!deque.isEmpty()) {
                    char queueOne = deque.removeFirst();
                    buffer.add(queueOne);

                    int index = A.length() - 1;
                    while(index >= 0 && buffer.size() >= A.length()) {
                        char censorOne = A.charAt(index);
                        char bufferOne = buffer.get(buffer.size() - A.length() + index);

                        index--;
                        if(censorOne != bufferOne) break;
                    }

//                    System.out.println(deque);
//                    System.out.println(buffer);
//
//                    Thread.sleep(500L);

                    if(index == -1) { // 검열 성공
                        for(int i = 0; i < A.length(); i++) {
                            buffer.remove(buffer.size() - 1);
                        }

                        find = 1;

                        while(buffer.size() > 0) {
                            deque.addFirst(buffer.remove(buffer.size() - 1));
                        }
                        break;
                    }
                }

                while(buffer.size() > 0) {
                    deque.addFirst(buffer.remove(buffer.size() - 1));
                }

                first = false;
            } else {
                while(!deque.isEmpty()) {
                    char queueOne = deque.removeLast();
                    buffer.add(queueOne);

//                    System.out.println(deque);
//                    System.out.println(buffer);
//
//                    Thread.sleep(500L);

                    int index = 0;
                    while(index < A.length() && buffer.size() >= A.length()) {
                        char censorOne = A.charAt(index);
                        char bufferOne = buffer.get(buffer.size() - index - 1);

                        index++;
                        if(censorOne != bufferOne) break;
                    }


                    if(index == A.length()) { // 검열 성공
                        for(int i = 0; i < A.length(); i++) { // 문자열 갯수만큼 제거
                            buffer.remove(buffer.size() - 1);
                        }

                        find = 1;

                        while(buffer.size() > 0) { // 버퍼에 추가
                            deque.addLast(buffer.remove(buffer.size() - 1));
                        }
                        break;
                    }
                }

                while(buffer.size() > 0) { // 버퍼에 추가
                    deque.addLast(buffer.remove(buffer.size() - 1));
                }
                first = true;
            }
        } while(find > 0);

        for(char ch : deque) {
            System.out.print(ch);
        }
    }
}
