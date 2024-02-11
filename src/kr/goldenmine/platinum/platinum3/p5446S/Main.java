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

        boolean bfsVisited;

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

        public char getChar(int index) {
            char ch;
            if(0 <= index && index < 10) {
                ch = (char) (index + '0');
            } else if(10 <= index && index < 36) {
                ch = (char) (index + 'a' - 10);
            } else if(36 <= index && index < 62) {
                ch = (char) (index + 'A' - 10 - 26);
            } else {
                ch = '.';
            }
            return ch;
        }

        public void add(String text, int pos, boolean banned) {
            char ch = text.charAt(pos);
            int index = getIndex(ch);

//            this.banned = this.banned | banned;
//            System.out.println(text + ", " + pos + ", " + banned);

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
            return dfs(this, "");
        }

        public int dfs(TrieNode node, String previous) {
            if(!node.banned) {
//                System.out.println(previous);
                return 1;
            }

            int count = 0;
            for(int i = 0; i < childs.length; i++) {
                TrieNode child = node.childs[i];
                if(child == null) continue;

                if(child.isLeaf && child.banned) {
                    count++;
                }

                count += dfs(child, previous + getChar(i));

//                if(!child.banned || child.isLeaf) { // 밴상태 아니거나 끝에 도달시 카운트 증가
//                    System.out.println(previous + getChar(i));
//                    count++;
//                } else {
//                    count += dfs(child, previous + getChar(i));
//                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int T = scan.nextInt();

        while(T-- > 0) {
            int N = scan.nextInt();
            List<String> toDelete = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                toDelete.add(scan.next());
            }

            int M = scan.nextInt();
            List<String> toSave = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                toSave.add(scan.next());
            }

            TrieNode root = new TrieNode(true);

            for (String delete : toDelete) {
                root.add(delete, 0, false);
            }

            for (String save : toSave) {
                root.banned = true;
                root.add(save, 0, true);
            }

            int res = root.dfs();

            System.out.println(res);
        }
    }
}
