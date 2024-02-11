package kr.goldenmine.baekjoon.platinum.platinum5.p14003A;

import java.io.*;
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

    public static int binarySearch(int[] arr, int size, int value) {
        int left = 0;
        int right = size;

        while (left < right) {
            int mid = (left + right) / 2;

            int arrValue = arr[mid];

            if (arrValue >= value) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    //https://www.acmicpc.net/problem/12015
    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        List<Integer> list = new ArrayList<>();
        // 입력된 수열을 저장
        int[] arr = new int[N + 1];
        // 입력된 각 수열의 위치를 저장
        int[] indexArr = new int[N + 1];

        // 수열을 입력받아 저장한다.
        for (int i = 1; i <= N; i++)
            arr[i] = scan.nextInt();

        list.add(Integer.MIN_VALUE);

        for (int i = 1; i <= N; i++) {
            int num = arr[i];
            int left = 1;
            int right = list.size() - 1;

            // 확인하는 숫자가 수열의 마지막 수보다 큰 경우
            // 수열에 추가해준다.
            if (num > list.get(list.size() - 1)) {
                list.add(num);
                indexArr[i] = list.size() - 1;
            }
            // 확인하는 숫자가 수열의 마지막 수보다 작은 경우
            else {
                while (left < right) {
                    int mid = (left + right) >> 1;

                    if (list.get(mid) >= num) right = mid;
                    else left = mid + 1;
                }
                list.set(right, num);
                indexArr[i] = right;
            }
//            System.out.println(list);
//            System.out.println(Arrays.toString(indexArr));
        }

//        System.out.println(Arrays.toString(arr));

        // 최장 길이 출력
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(String.valueOf(list.size() - 1));
        writer.newLine();

        // 역추적 경로를 저장할 stack
        Stack<Integer> stack = new Stack<>();

        // 현재 찾길 원하는 증가수열의 인덱스 값
        int index = list.size() - 1;

        for (int i = N; i > 0; i--) {
            // 찾길 원하는 인덱스와 같은 경우
            if (indexArr[i] == index) {
                // 찾길 원하는 인덱스를 하나 감소시킨다.
                // 다음 인덱스의 값을 찾기 위해서
                index--;
                // stack에 경로를 추가한다.
                stack.push(arr[i]);
            }
        }

        // 스택에서 꺼내며 찾는다.
        while (!stack.isEmpty()) {
            writer.write(String.valueOf(stack.pop()));
            writer.write(" ");
        }

        writer.close();
//        for(int i = 0; i < size; i++) {
//            System.out.print(LIS[i] + " ");
//        }
    }
}
