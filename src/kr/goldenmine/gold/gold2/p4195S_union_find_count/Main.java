package kr.goldenmine.gold.gold2.p4195S_union_find_count;

import java.io.*;
import java.util.HashMap;
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

    static class Node {
        int parent;

        int size;

        public Node(int parent, int size) {
            this.parent = parent;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "parent=" + parent +
                    ", size=" + size +
                    '}';
        }
    }

    public static Node find(Node[] arr, Node value) {
        if(arr[value.parent].parent == value.parent)
            return value;
        return arr[value.parent] = find(arr, arr[value.parent]);
    }

    public static void merge(Node[] arr, Node x, Node y) {
        x = find(arr, x);
        y = find(arr, y);
        if(x.parent == y.parent) return;
        arr[y.parent] = x;
        x.size += y.size;
    }

    public static boolean isUnion(Node[] arr, Node x, Node y) {
        x = find(arr, x);
        y = find(arr, y);

        return x.parent == y.parent;
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        int T = scan.nextInt();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < T; i++) {
            int N = scan.nextInt();

            Node[] arr = new Node[N * 2 + 1];
            for(int j = 1; j <= N * 2; j++) {
                arr[j] = new Node(j, 1);
            }

            int index = 1;
            HashMap<String, Integer> map = new HashMap<>();

            for(int j = 0; j < N; j++) {
                String a = scan.next();
                String b = scan.next();

                if(!map.containsKey(a)) {
                    map.put(a, index++);
                }
                if(!map.containsKey(b)) {
                    map.put(b, index++);
                }

                merge(arr, arr[map.get(a)], arr[map.get(b)]);
                writer.write(String.valueOf(find(arr, arr[map.get(a)]).size));
                writer.newLine();
//                writer.write(String.valueOf(find(arr, arr[map.get(b)]).size));
//                writer.newLine();

//                System.out.println(Arrays.toString(arr));
//                System.out.println(map);
            }
        }

        writer.close();
    }
}
