package kr.goldenmine.platinum.platinum3.p5446S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
        TrieNode[] childs = new TrieNode[63];
        int childCount = 0;
        boolean banned;
        boolean isLeaf;
        boolean root;

        public TrieNode(boolean root) {
            this.root = root;
        }

        public int getIndex(char ch) {
            int index;
            if ('0' <= ch && ch <= '9') {
                index = ch - '0'; // 10개
            } else if ('a' <= ch && ch <= 'z') {
                index = ch - 'a' + 10; // 26개
            } else if ('A' <= ch && ch <= 'Z') {
                index = ch - 'A' + 10 + 26;
            } else if (ch == '.') {
                index = 62;
            } else {
                throw new RuntimeException("?");
            }

            return index;
        }

        public void add(String text, int pos, boolean banned) {
            char ch = text.charAt(pos);
            int index = getIndex(ch);

            if (childs[index] == null) {
                childs[index] = new TrieNode(false);
                if(!banned)
                    childCount++;
            }

            childs[index].banned = banned;

            if (pos < text.length() - 1) { // last
                childs[index].add(text, pos + 1, banned);
            } else {
                if(!banned)
                    childs[index].isLeaf = true;
            }
        }

        public int dfs() {
            return dfs(this);
        }

        public int dfs(TrieNode node) {
            if(!node.banned) {
                return 1;
            }

            int count = 0;
            for(int i = 0; i < childs.length; i++) {
                TrieNode child = node.childs[i];
                if(child == null) continue;

                if(child.isLeaf && child.banned) {
                    count++;
                }

                count += dfs(child);
            }
            return count;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int T = scan.nextInt();

        while(T-- > 0) {
            TrieNode root = new TrieNode(true);

            int N = scan.nextInt();
            for (int i = 0; i < N; i++) {
                root.add(scan.next(), 0, false);
            }

            int M = scan.nextInt();

            for (int i = 0; i < M; i++) {
                root.banned = true;
                root.add(scan.next(), 0, true);
            }

            int res = root.dfs();

            System.out.println(res);
        }
    }
}
