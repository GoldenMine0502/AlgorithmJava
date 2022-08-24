package kr.goldenmine;

public class UnionFind {
    public static int find(int[] arr, int value) {
        if(arr[value] == value)
            return value;
        return arr[value] = find(arr, arr[value]);
    }

    public static void merge(int[] arr, int x, int y) {
        x = find(arr, x);
        y = find(arr, y);
        if(x == y) return;
        arr[y] = x;
    }

    public static boolean isUnion(int[] arr, int x, int y) {
        x = find(arr, x);
        y = find(arr, y);

        return x == y;
    }
}
