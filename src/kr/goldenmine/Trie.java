package kr.goldenmine;

public class Trie {
    static class TrieNode {
        TrieNode[] childs = new TrieNode[26];
        int childCount = 0;
        boolean isLeaf;
        boolean root;

        public TrieNode(boolean root) {
            this.root = root;
        }

        public void add(String text, int pos) {
            int index = text.charAt(pos) - 'a';

            if (childs[index] == null) {
                childs[index] = new TrieNode(false);
                childCount++;
            }
            if (pos < text.length() - 1) { // last
                childs[index].add(text, pos + 1);
            } else {
                childs[index].isLeaf = true;
            }
        }
    }
}
