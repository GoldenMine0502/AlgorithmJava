package kr.goldenmine.contest.inu2024.p6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

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
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char)c;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32);
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -12345; }
            if (c == 45) { c = read(); isMinus = true; }
            else if (c == 46) { c = read(); }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c){
            return (64 < c && c < 91) || (96 < c && c < 123);
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

    static class Gift {
        int size;
        int giftsize;

        Gift(int size, int giftsize) {
            this.size = size;
            this.giftsize = giftsize;
        }

        @Override
        public String toString() {
            return "Gift{" +
                    "size=" + size +
                    ", giftsize=" + giftsize +
                    '}';
        }
    }

    static int lowerBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            /*
             *  key 값이 중간 위치의 값보다 작거나 같을 경우
             *
             *  (중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.)
             */
            if (key <= arr[mid]) {
                hi = mid;
            }

            else {
                lo = mid + 1;
            }

        }

        return lo;
    }

    static int upperBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {
            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            // key값이 중간 위치의 값보다 작을 경우
            if (key < arr[mid]) {
                hi = mid;
            }
            // 중복원소의 경우 else에서 처리된다.
            else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[] gifts = new int[N];
        for(int i = 0; i < N; i++) {
            gifts[i] = scan.nextInt();
        }
        Arrays.sort(gifts);

        int[] giftsizes = new int[N];
        for(int i = 0; i < N; i++) {
            giftsizes[i] = scan.nextInt();
        }

        Arrays.sort(giftsizes);

//        System.out.println(Arrays.toString(gifts));
//        System.out.println(Arrays.toString(giftsizes));

        Gift[] giftobjs = new Gift[N];
        for(int i = 0; i < N; i++) {
            giftobjs[i] = new Gift(gifts[upperBound(gifts, giftsizes[i]) - 1], giftsizes[i]);
//            System.out.println(giftobjs[i]);
        }

//        System.out.println(Arrays.toString(giftobjs));

        HashMap<Integer, Integer> counts = new HashMap<>();

        for(Gift g : giftobjs) {
            if(counts.containsKey(g.giftsize)) {
                counts.put(g.giftsize, counts.get(g.giftsize) + 1);
            } else {
                counts.put(g.giftsize, 1);
            }
        }
//        System.out.println(counts);

        for(int i = 0; i < M; i++) {
            int input = scan.nextInt();
            int remain = counts.get(input);

            if(remain >= 2) {
                counts.put(input, counts.get(input) - 1);
            } else {
                counts.remove(input);
            }
        }

//        System.out.println(counts);

        int max = 0;
        for(int v : counts.keySet()) {
            max = Math.max(max, v);
        }

        for(int i = 0; i < N; i++) {
            if(giftobjs[i].giftsize == max) {
                System.out.println(giftobjs[i].size);
                return;
            }
        }

//        for(int i = 0; i < N; i++) {
//            gifts[i].giftsize = scan.nextInt();
//        }

//        Arrays.sort(gifts, new Comparator<Gift>() {
//            @Override
//            public int compare(Gift o1, Gift o2) {
//                if(o1.giftsize == o2.giftsize) {
//                    return o1.size - o2.size;
//                } else {
//                    return o1.giftsize - o2.giftsize;
//                }
//            }
//        });
//
//        System.out.println(Arrays.toString(gifts));
//
//        // 이분 탐색으로 찾기
//        for(int i = 0; i < M; i++) {
//            int input = scan.nextInt();
//
//            int index = lowerBound(gifts, input);
//            gifts[index].giftsize = -1;
//        }
//
//        int max = 0;
//        int maxValue = 0;
//        for(int i = 0; i < N; i++) {
//            Gift gift = gifts[i];
//            if(max < gift.giftsize) {
//                max = gift.giftsize;
//                maxValue = gift.size;
//            }
//        }
//        System.out.println(maxValue);
    }
}
