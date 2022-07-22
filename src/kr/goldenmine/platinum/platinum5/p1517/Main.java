package kr.goldenmine.platinum.platinum5.p1517;

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

    static class LongSumSegmentTree {
        private long[] arr;
        private long[] tree;
        private int[] indices;

        public LongSumSegmentTree(long[] arr) {
            this.arr = arr;
            this.indices = new int[arr.length];
            this.tree = new long[4 * arr.length];
            init(0, arr.length - 1, 1);
        }

        public long init(int start, int end, int node) {
            if(start == end) {
                indices[start] = node;
                return this.tree[node] = arr[start];
            }

            int mid = (start + end) / 2;
            return this.tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        }

        public long sum(int left, int right) {
            return sum(0, arr.length - 1, 1, left, right);
        }

        public long sum(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return 0;

            // 범위 안에 있는 경우
            if(left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우

            int mid = (start + end) / 2;

            return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
        }

        public void updateSet(int index, long value) {
            updateSet(0, arr.length - 1, 1, index, value);
        }

        public void updateSet(int start, int end, int node, int index, long value) {
            long diff = value - arr[index];

            update(start, end, node, index, diff);
        }

        public void update(int index, long diff) {
            update(0, arr.length - 1, 1, index, diff);
        }

        public void update(int start, int end, int node, int index, long diff) {
            if(index < start || index > end) return;

            tree[node] += diff;

            if (start == end) return;

            int mid = (start + end) / 2;

            update(start, mid, node * 2, index, diff);
            update(mid + 1, end, node * 2 + 1, index, diff);

            if(node == 1) {
                arr[index] += diff;
            }
        }

        public long findOneValue(int index) {
            return tree[(int) indices[index]];
        }

        public void updateRanged(int range1, int range2, long diff) {
            updateRanged(0, arr.length - 1, 1, range1, range2, diff);
        }

        // 리턴값: 나보다 아래 노드에서 바뀐 횟수
        public int updateRanged(int start, int end, int node, int range1, int range2, long diff) {
//            System.out.println("update  " + node + ", (" + start + ", " + end + "), (" + range1 + ", " + range2 + ")");
            if(end < range1 && range2 > start) return 0;

            if (start == end) {
                tree[node] += diff;
                return 1;
            }

            int mid = (start + end) / 2;

            int count = updateRanged(start, mid, node * 2, range1, range2, diff) + updateRanged(mid + 1, end, node * 2 + 1, range1, range2, diff);

//            System.out.println("update2 " + node + ", (" + start + ", " + end + "), (" + range1 + ", " + range2 + ")");
//            System.out.println("count " + node + ", " + count);
            tree[node] += diff * count;

            return count;
        }

        public void printTreeAll() {
            for(int i = 0; i < tree.length; i++) {
                System.out.print(tree[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

    }
}
