package kr.goldenmine.templates;

public class SegmentTrees {
    static class LongSumSegmentTree {
        private long[] arr;
        private long[] tree;

        public LongSumSegmentTree(long[] arr) {
            this.arr = arr;
            this.tree = new long[4 * arr.length];
            init(0, arr.length - 1, 1);
        }

        public long init(int start, int end, int node) {
            if(start == end) return this.tree[node] = arr[start];

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

        public void set(int index, long value) {
            set(0, arr.length - 1, 1, index, value);
        }

        public void set(int start, int end, int node, int index, long value) {
            long diff = value - arr[index];

            add(start, end, node, index, diff);
        }

        public void add(int index, long diff) {
            add(0, arr.length - 1, 1, index, diff);
        }

        public void add(int start, int end, int node, int index, long diff) {
            if(index < start || index > end) return;

            tree[node] += diff;

            if (start == end) {
                if(node == 1) arr[index] += diff;
                return;
            }

            int mid = (start + end) / 2;

            add(start, mid, node * 2, index, diff);
            add(mid + 1, end, node * 2 + 1, index, diff);

            if(node == 1) arr[index] += diff;
        }

        public void printTreeAll() {
            for(int i = 0; i < tree.length; i++) {
                System.out.print(tree[i] + " ");
            }
            System.out.println();
        }
    }

    static class LongSumLazySegmentTree {
        long[] arr;
        long[] tree;
        long[] lazy;

        LongSumLazySegmentTree(long[] arr) {
            this.arr = arr;
            this.tree = new long[4 * arr.length];
            this.lazy = new long[4 * arr.length];
            init(0, arr.length - 1, 1);
        }

        long init(int start, int end, int node) {
            if(start == end) return this.tree[node] = arr[start];

            int mid = (start + end) / 2;
            return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        }

        void propagate(int start, int end, int node) {
            if(lazy[node] !=0){
                if(start != end) {
                    lazy[node*2] += lazy[node];
                    lazy[node*2 +1] += lazy[node];
                }
                tree[node] += lazy[node] * (end-start+1);
                lazy[node]=0;
            }
        }

        void add(int index, long dif) {
            add(index, index, dif);
        }

        void add(int left, int right, long dif) {
            add(0, arr.length - 1, 1, left, right, dif);
        }

        void add(int start, int end, int node, int left, int right, long dif) {
            propagate(start, end, node);
            if(end < left || right < start) return;
            if(left <= start && end <= right) {
                lazy[node] = dif;
                propagate(start, end, node);
                return;
            }

            int mid = (start+end)/2;
            add(start, mid, node*2, left, right, dif);
            add(mid+1, end, node*2+1, left, right, dif);

            tree[node] = tree[node*2]+tree[node*2+1];
        }

        long get(int index) {
            return sum(index, index);
        }

        long sum(int left, int right) {
            return sum(0, arr.length - 1, 1, left, right);
        }

        long sum(int start, int end, int node, int left, int right) {
            propagate(start, end, node);
            if(end < left || right < start) return 0;
            if(left <= start && end <= right) return tree[node];

            int mid = (start+end)/2;
            return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
        }
    }

    static class ContainSegmentTree {
        private char[] arr;
        private int[] tree;
        private char ch;

        public ContainSegmentTree(char[] arr, char ch) {
            this.arr = arr;
            this.tree = new int[4 * arr.length];
            this.ch = ch;
            init(0, arr.length - 1, 1);
        }

        public int init(int start, int end, int node) {
            if(start == end) return this.tree[node] = (arr[start] == ch ? 1 : 0);

            int mid = (start + end) / 2;
            return this.tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        }

        public int sum(int left, int right) {
            return sum(0, arr.length - 1, 1, left, right);
        }

        public int sum(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return 0;

            // 범위 안에 있는 경우
            if(left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우

            int mid = (start + end) / 2;

            return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
        }

        public void printTreeAll() {
            for(int i = 0; i < tree.length; i++) {
                System.out.print(tree[i] + " ");
            }
            System.out.println();
        }
    }

}
