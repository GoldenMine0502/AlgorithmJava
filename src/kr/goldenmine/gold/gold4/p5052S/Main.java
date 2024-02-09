package kr.goldenmine.gold.gold4.p5052S;

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

    static class TrieNode {
        TrieNode[] list = new TrieNode[10];
        boolean isLeaf;

        public TrieNode() {

        }

        public boolean add(String str, int pos) {
//            System.out.println(str + ", " + pos);

            if(str.length() == pos) {
//                System.out.println(str + ", " + pos + ", " + isLeaf);
                isLeaf = true;
                for(int i = 0; i <= 9; i++) {
                    if(list[i] != null) {
//                        System.out.println(1);
                        return false;
                    }
                }
                return true;
            }

            int tel = str.charAt(pos) - '0';

            if(list[tel] == null) {
                list[tel] = new TrieNode();
            }
            else if(list[tel].isLeaf) {
//                System.out.println(tel + ", " + pos + ", ");
                return false;
            }

            return list[tel].add(str, pos + 1);
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int T = scan.nextInt();

        for(int t = 0; t < T; t++) {
            int N = scan.nextInt();
            TrieNode node = new TrieNode();
            boolean res = true;
            for(int i = 0; i < N; i++) {
                String text = scan.next();
//                System.out.println(text);
                res = res && node.add(text, 0);

//                if(!res) break;
            }
            if(res) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
