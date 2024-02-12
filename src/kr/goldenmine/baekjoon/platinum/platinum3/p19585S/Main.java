package kr.goldenmine.baekjoon.platinum.platinum3.p19585S;

import java.io.*;
import java.util.*;

public class Main {
    final private static int BUFFER_SIZE = 1 << 12;
    private static DataInputStream din = new DataInputStream(System.in);
    private static byte[] buffer = new byte[BUFFER_SIZE];
    private static int bufferPointer = 0, bytesRead = 0;
    private static byte[] buf = new byte[2100]; // line length

    public static String readLine() throws IOException {
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                if (cnt != 0) {
                    break;
                } else {
                    continue;
                }
            }
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public static int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg)
            return -ret;
        return ret;
    }

    public static long nextLong() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg)
            return -ret;
        return ret;
    }

    public static double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();

        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg)
            return -ret;
        return ret;
    }

    private static void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0,
                BUFFER_SIZE);
        if (bytesRead == -1)
            buffer[0] = -1;
    }

    private static byte read() throws IOException {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }

    public static void close() throws IOException {
        if (din == null)
            return;
        din.close();
    }

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
        TrieNode[] childs = new TrieNode[26];
        //        HashMap<Integer, TrieNode> childs = new HashMap<>();
//        int childCount = 0;
        boolean isLeaf;
//        boolean root;

        public void addNotRecursive(String text) {
            TrieNode cursor = this;

            for (int i = 0; i < text.length(); i++) {
                int index = text.charAt(i) - 'a';

                if (cursor.childs[index] == null) {
                    cursor.childs[index] = new TrieNode();
                }

                TrieNode child = cursor.childs[index];

                if (i == text.length() - 1) {
                    child.isLeaf = true;
                } else {
                    cursor = child;
                }
            }
        }

//        public void add(String text, int pos) {
//            int index = text.charAt(pos) - 'a';
//
//            if (childs[index] == null) {
//                childs[index] = new TrieNode();
////                childCount++;
//            }
//            if (pos < text.length() - 1) { // last
//                childs[index].add(text, pos + 1);
//            } else {
//                childs[index].isLeaf = true;
//            }
//        }

        public boolean findNotRecursive(String text, int p, List<Integer> finds, boolean find) {
            TrieNode cursor = this;
            for (int pos = p; pos < text.length(); pos++) {
                int index = text.charAt(pos) - 'a';

//                if(maxLen != -1 && pos == p + maxLen) break;

                TrieNode child = cursor.childs[index];
                if (child != null) {
                    cursor = child;

                    if (cursor.isLeaf) {
                        finds.add(pos + 1);
                        if (find) {
                            return true;
                        }
                    }
                } else {
                    break;
                }
            }
            return false;
        }

//        public void find(String text, int pos, List<Integer> finds, boolean breakFind) {
//            if(isLeaf) {
//                finds.add(pos);
//                if(breakFind) return;
//            }
//
//            if(pos == text.length()) return;
//
//            int index = text.charAt(pos) - 'a';
//
//
//            if(childs[index] == null) {
//                // continue
//            } else {
//                childs[index].find(text, pos + 1, finds, breakFind);
//            }
//        }
    }

    public static void main(String[] args) throws IOException {
        int C = nextInt();
        int N = nextInt();

        TrieNode colours = new TrieNode();
        TrieNode names = new TrieNode();

//        List<String> colorInput = new ArrayList<>();
//        List<String> nameInput = new ArrayList<>();
//
//        for(int i = 0; i < C; i++) {
//            colorInput.add(readLine());
//        }
//
//        for(int i = 0; i < N; i++) {
//            nameInput.add(readLine());
//        }
//
//        for(int i = 0; i < C; i++) {
//            for(int j = 0; j < N; j++) {
//                colours.addNotRecursive(colorInput.get(i) + nameInput.get(j));
//            }
//        }

        int cMax = 0, nMax = 0;
        int cMin = 1001, nMin = 1001;

        for (int i = 0; i < C; i++) {
//            colours.add(scan.next(), 0);
            String str = readLine();
            int len = str.length();
            if (len < cMin) cMin = len;
            if (len > cMax) cMax = len;
            colours.addNotRecursive(str);
        }

        HashSet<String> nicknames = new HashSet<>();

        for (int i = 0; i < N; i++) {
//            names.add(scan.next(), 0);
            String str = readLine();
            int len = str.length();
            if (len < nMin) nMin = len;
            if (len > nMax) nMax = len;
//            names.addNotRecursive(str);
            nicknames.add(str);
        }

        int Q = Integer.parseInt(readLine());
        List<Integer> coloursList = new ArrayList<>();
        List<Integer> namesList = new ArrayList<>();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            String text = readLine();
//            writer.append(colours.findNotRecursive(text, 0) ? "Yes" : "No");
//            writer.newLine();
            boolean result = false;

            if (text.length() <= cMax + nMax) {
                coloursList.clear();
//            colours.find(text, 0, coloursList, false);
                colours.findNotRecursive(text, 0, coloursList, false);

//            Collections.sort(coloursList, Collections.reverseOrder());

                for(int j : coloursList) {
                    result = nicknames.contains(text.substring(j));
                    if(result) break;
                }

//                for (int j = 0; j < coloursList.size(); j++) {
//                    int index = coloursList.get(j);
////                System.out.println(index);
//
////                names.find(text, index, namesList, true);
//                    boolean res = names.findNotRecursive(text, index, namesList, true);
//
//                    if (res) {
//                        result = true;
//                        break;
//                    }
//                }
//                namesList.clear();
            }
            writer.append(result ? "Yes" : "No");
            writer.newLine();
        }
//        writer.flush();
        writer.close();
//        br.close();
    }
}

/*
4 3
red
re
r
orange
shift
joker
noon
5
rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrredshift
bluejoker
purplenoon
orangeshift
whiteblue

 */
