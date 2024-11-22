package kr.goldenmine.baekjoon.gold.gold2.p2352;

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

    static int[] dp;

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        // dp에 실질적으로 저장된 원소의 길이 = LIS인 1차원 dp테이블을 만든다.
        // 해당 dp에 저장된 원소(0이 아닌 값)들은 이후 조사하는 원소들이 부분 수열을 늘릴 수 있을지에 대한 정보를 제공한다.
        dp = new int[N];
        // 처음에 저장된 원소는 없으므로, LIS = 0이다.
        int LIS = 0;

        // 첫 번째 원소부터 N번째 원소까지 dp 테이블의 값을 채워 나간다.
        for (int i = 0; i < N; i++) {
            // 이분 탐색을 활용하여 dp테이블에 저장된 원소를 탐색하며 현재 선택된 숫자가 dp테이블의 어떤 위치에 포함될지 파악한다.
            int idx = BinarySearch(arr[i], 0, LIS, LIS + 1);

            // 찾지 못한 경우
            if(idx == -1) {
                // 가장 마지막 위치에 원소를 삽입하고 LIS의 길이를 늘린다.
                dp[LIS++] = arr[i];
            }
            // 찾은 경우
            else {
                // 해당 위치에 현재 값을 삽입하여 갱신한다.
                dp[idx] = arr[i];
            }
        }

        // LIS의 길이를 출력한다.
        System.out.println(LIS);
    }

    private static int BinarySearch(int num, int start, int end, int size) {
        int res = 0;
        while (start <= end) {
            // 중앙 값을 찾는다.
            int mid = (start + end) / 2;

            // 만일 현재 선택된 원소가 해당 원소보다 작거나 같다면, 앞 부분을 탐색한다.
            if (num <= dp[mid]) {
                // 해당 원소의 위치를 기억해둔다.
                res = mid;
                end = mid - 1;
            }
            // 만일 현재 선택된 원소가 해당 원소보다 크다면, 뒷 부분을 탐색한다.
            else {
                start = mid + 1;
            }
        }

        // dp테이블에서 삽입될 위치를 찾지 못한 경우(즉, 모든 수들보다 큰 경우).
        if (start == size) {
            return -1;
        }
        // dp테이블에서 삽입될 위치를 찾은 경우.
        else {
            return res;
        }
    }
}
