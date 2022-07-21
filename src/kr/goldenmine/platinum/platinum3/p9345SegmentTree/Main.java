package kr.goldenmine.platinum.platinum3.p9345SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    static class SegmentTree {
        private int[] arr;
        private int[] available;

        public SegmentTree(int[] arr) {
            this.arr = arr;
            this.available = new int[4 * arr.length];
            init(0, arr.length - 1, 1);
        }

        // 대충 다 1(빌리기 가능)으로 설정
        public int init(int start, int end, int node) {
            if(start == end) return this.available[node] = 1;

            int mid = (start + end) / 2;

            return this.available[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        }

        public int count(int left, int right) {
            return count(0, arr.length - 1, 1, left, right, left, right);
        }

        public int count(int start, int end, int node, int left, int right, int leftOriginal, int rightOriginal) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return 0;

            System.out.println("visit: " + node + ", " + start + ", " + end + ", " + left + ", " + right);
            // 범위 안에 있는 경우
            if(left <= start && end <= right) {
//                if(available[node] == 1) {
//                    return available[node];
//                } else {
//                    return
//                }
                return available[node];
            }


            // 범위가 걸쳐있는 경우
            int mid = (start + end) / 2;

            return count(start, mid, node * 2, left, right, leftOriginal, rightOriginal) + count(mid + 1, end, node * 2 + 1, left, right, leftOriginal, rightOriginal);
        }

        public void updateDiff(int index, int diff) {
            updateDiff(0, arr.length - 1, 1, index, diff);
        }

        public void update(int index, int value) {
            int diff = value - arr[index];

            updateDiff(index, diff);
        }

        public void updateDiff(int start, int end, int node, int index, int diff) {
            if(index < start || index > end) return;


            int value = arr[index] + diff;
            if(start <= value && value <= end) {
                available[node] = 1;
            } else {
                available[node] = 0;
            }
//            System.out.println("update: [" + node + "] " + start + " <= " + value + " < " + end);

            if (start == end) return;

            int mid = (start + end) / 2;

            updateDiff(start, mid, node * 2, index, diff);
            updateDiff(mid + 1, end, node * 2 + 1, index, diff);

            if(node == 1) {
                arr[index] += diff;
            }
        }

        public void printArrAll() {
            for(int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        public void printTreeAll() {
            for(int i = 0; i < available.length; i++) {
                System.out.print(available[i] + " ");
            }
            System.out.println();
        }

        public void printAll() {
            for(int i = 0; i < available.length - 1; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            printArrAll();
            printTreeAll();
        }
    }

    static FastReader scan = new FastReader();

    public static void solve() {
        int N = scan.nextInt(); // DVD의 수
        int K = scan.nextInt(); /// 사건의 수

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = i;
        }

        SegmentTree tree = new SegmentTree(arr);
        tree.printAll();

        for(int i = 0; i < K; i++) {
            int Q = scan.nextInt();
            int A = scan.nextInt();
            int B = scan.nextInt();

            if(Q == 0) { // 진일이가 ㅈㄹ하는 상황
                tree.update(A, B);
                tree.update(B, A);

//                tree.printAll();
            } else { // 손님이 빌리는 상황
                System.out.println(tree.count(A, B));
//                if(tree.count(A, B) == 1) {
//                    System.out.println("YES");
//                } else {
//                    System.out.println("NO");
//                }
            }
        }
    }

    public static void main(String[] args) {
//        int T = scan.nextInt();
//
//        for(int i = 0; i < T; i++) {
//            solve();
//        }

//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] arr = {1,2,3,4,5};

        SegmentTree tree = new SegmentTree(arr);
        tree.printAll();

//        tree.update(2, 4);
//        tree.update(3, 3);
//        tree.printAll();
//        System.out.println(tree.count(0, 4));
//        System.out.println(tree.count(0, 1));
//        System.out.println(tree.count(0, 2));
//        System.out.println(tree.count(0, 3));
//
//        tree.update(3, 3);
//        tree.printAll();
//        System.out.println(tree.count(0, 4));


    }
}
