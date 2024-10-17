package kr.goldenmine.templates;

public class UnionFind {
    static int find(int[] parent, int value) {
        if(parent[value] == value)
            return value;
        return parent[value] = find(parent, parent[value]); // path compression
    }

    // by rank
    static void union(int[] parent, int[] rank, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if(x == y) return;

        if(rank[x] < rank[y]) {
            parent[x] = y;
            rank[y] += rank[x];
        } else {
            parent[y] = x;
            rank[x] += rank[y];
        }
    }

    static boolean isUnion(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        return x == y;
    }
}
