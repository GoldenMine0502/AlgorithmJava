package kr.goldenmine.baekjoon.gold.gold3.p20440A;

import java.util.*;
import java.util.function.BiFunction;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (47 < (c = read()) && c < 58);
            return n;
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

        HashMap<Integer, Integer> map = new HashMap<>();

        int N = scan.nextInt();

        BiFunction<Integer, Integer, Integer> sum = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        };

        for(int i = 0; i < N; i++) {
            int start = scan.nextInt();
            int end = scan.nextInt();

            map.merge(start, 1, sum);
            map.merge(end, -1, sum);
        }

//        System.out.println(map);

        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort(Integer::compareTo);

        int[] arr = new int[list.size() + 1];
//        System.out.println(Arrays.toString(arr));

        int max = 0;
        int maxStart = -1;
        int maxEnd = -1;

        for(int i = 0; i < list.size(); i++) {
            int key = list.get(i);
            int value = map.get(key);
//            System.out.println(key + ", " + value);

            arr[i + 1] = arr[i] + value;
            if(arr[i + 1] > max) {
                max = arr[i + 1];
                maxStart = key;
                maxEnd = list.get(i + 1); // 마지막 값은 이전 값보다 클 수 없어서 오류날 일이 없음
            }

            if(arr[i + 1] == max && maxEnd == key) { // max가 같으면 연장함
                maxEnd = list.get(i + 1);
            }
        }

        System.out.println(max);
        System.out.println(maxStart + " " + maxEnd);
    }
}
