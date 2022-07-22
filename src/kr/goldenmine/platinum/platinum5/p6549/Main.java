package kr.goldenmine.platinum.platinum5.p6549;

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


    static class MinMaxSegmentTree {
//        class Node {
//            int height;
//            int maxArea;
//
//            public Node(int min, int max) {
//                this.height = min;
//                this.maxArea = max;
//            }
//
//            @Override
//            public String toString() {
//                return "(" + height + ", " + maxArea + ")";
//            }
//        }

        class Node {
            // 왼쪽과 가까운 곳 넓이
            int aLeft;
            int aRight;
            long aHeight;

            // 오른쪽과 가까운 곳 넓이
            int bLeft;
            int bRight;
            long bHeight;

            long maxArea;

            Node(int aLeft, int aRight, long aHeight, int bLeft, int bRight, long bHeight, long maxArea) {
                this.aLeft = aLeft;
                this.aRight = aRight;
                this.aHeight = aHeight;
                this.bLeft = bLeft;
                this.bRight = bRight;
                this.bHeight = bHeight;
                this.maxArea = maxArea;
            }

            public long getLeftArea() {
                return (aRight - aLeft + 1) * aHeight;
            }

            public long getRightArea() {
                return (bRight - bLeft + 1) * bHeight;
            }

            @Override
            public String toString() {
                return "(" + aLeft + ", " + aRight + ", " + getLeftArea() + ", " + bLeft + ", " + bRight + ", " + getRightArea() + ", " + maxArea + ")";
            }
        }

        private long[] arr;
        private Node[] tree;

        public MinMaxSegmentTree(long[] arr) {
            this.arr = arr;
            this.tree = new Node[4 * arr.length];

//            for (int i = 0; i < this.tree.length; i++) {
//                this.tree[i] = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
//            }

            init(0, arr.length - 1, 1);
        }

        Node merge(Node a, Node b) {
            int aaLeft = a.aLeft; // 2
            int aaRight = a.aRight; // 2
            long aaHeight = a.aHeight;
            int bbLeft = b.bLeft; // 3
            int bbRight = b.bRight; // 3
            long bbHeight = b.bHeight;

            long max = Math.max(Math.max(a.getLeftArea(), a.getRightArea()), Math.max(b.getLeftArea(), b.getRightArea()));

            if(b.aLeft - a.aRight == 1) { // mixed 가능
                int mixedLeft = a.bLeft; // 2
                int mixedRight = b.aRight; // 3
                long mixedHeight = Math.min(a.bHeight, b.aHeight);

                long area = (mixedRight - mixedLeft + 1) * mixedHeight;

                // 확장됨
                if(aaLeft == mixedLeft && area > a.getLeftArea()) {
                    aaRight = mixedRight;
                    aaHeight = mixedHeight;
                }

                if(bbRight == mixedRight && area > b.getRightArea()) {
                    bbLeft = mixedLeft;
                    bbHeight = mixedHeight;
                }

                max = Math.max(max, area);
            } else { // mixed 불가능,

            }

            System.out.println(max);

            return new Node(aaLeft, aaRight, aaHeight, bbLeft, bbRight, bbHeight, max);
        }


        public Node init(int start, int end, int node) {
            if (start == end) return this.tree[node] = new Node(start, start, arr[start], start, start, arr[start], arr[start]);

            int mid = (start + end) / 2;

            Node left = init(start, mid, node * 2);
            Node right = init(mid + 1, end, node * 2 + 1);

            return this.tree[node] = merge(left, right);
        }

        public Node find(int left, int right) {
            return find(0, arr.length - 1, 1, left, right);
        }

        public Node find(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            // 대충 연산 못할만한 애로 설정
            if (left > end || right < start) return new Node(Integer.MAX_VALUE, Integer.MAX_VALUE, -1, Integer.MAX_VALUE, Integer.MAX_VALUE, -1, 0);

            // 범위 안에 있는 경우
            if (left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우
            int mid = (start + end) / 2;
            return merge(find(start, mid, node * 2, left, right), find(mid + 1, end, node * 2 + 1, left, right));
        }

        public void update(int index, int value) {
            update(0, arr.length - 1, 1, index, value);
        }

        public Node update(int start, int end, int node, int index, int value) {
//            System.out.println("access " + start + ", " + end + ", " + node + ", " + index);
            if (index < start || index > end) return tree[node];

            if (node == 1) {
                arr[index] = value;
            }

            if (start == end) { // 최하위 클래스
//                System.out.println("update node " + node + " to (" + value + ", " + value + "), " + start + " " + index);
                return tree[node] = new Node(value, value, arr[index], value, value, arr[index], arr[index]);
            }

            int mid = (start + end) / 2;

            Node a = update(start, mid, node * 2, index, value);
            Node b = update(mid + 1, end, node * 2 + 1, index, value);

//            System.out.println("update node " + node + " from" + tree[node] + " to " + merge(a, b) + ": " + a + " (" + start + ", " + mid + ") / " + b + " (" + (mid + 1) + ", " + end + ")" + " " + index);
            return tree[node] = merge(a, b);
        }

        public void printArrAll() {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        public void printTreeAll() {
            for (int i = 0; i < tree.length; i++) {
                System.out.print(tree[i] + " ");
            }
            System.out.println();
        }

        public void printAll() {
//            for(int i = 0; i < tree.length - 1; i++) {
//                System.out.print(i + " ");
//            }
            System.out.println();
            printArrAll();
            printTreeAll();
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        String line;

        while (!(line = scan.nextLine()).equals("0")) {
            String[] split = line.split(" ");

            long[] arr = new long[split.length - 1];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(split[i + 1]);
            }

            MinMaxSegmentTree tree = new MinMaxSegmentTree(arr);
            tree.printAll();
            long max = tree.find(0, arr.length).maxArea;

            System.out.println(max);
        }
    }
}
