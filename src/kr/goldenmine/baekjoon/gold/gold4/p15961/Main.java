package kr.goldenmine.baekjoon.gold.gold4.p15961;

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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int d = scan.nextInt();
        int k = scan.nextInt();
        int c = scan.nextInt();

        int[] arr = new int[N + k];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
            if(i < k) {
                arr[N + i] = arr[i];
            }
        }

        int[] counts = new int[d + 1];

        int left = 0;
        int right = 0;
        int count = 0;
        int max = 0;
        while(left < N) {
            if(right - left == k) {
                counts[arr[left]]--;
                if(counts[arr[left]] == 0) {
                    count--;
                }
                left++;
            }

            if(right - left < k) {
                counts[arr[right]]++;
                if(counts[arr[right]] == 1) {
                    count++;
                }

//                if(right < N + k) {
                    right++;
//                }
            }

            int coupon = counts[c] == 0 ? 1 : 0;
            max = Math.max(max, count + coupon);
//            System.out.println(left + ", " + right + ", " + count + ", " + coupon);
        }

        System.out.println(max);
    }
}
