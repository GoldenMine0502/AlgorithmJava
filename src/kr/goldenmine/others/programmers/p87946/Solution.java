package kr.goldenmine.others.programmers.p87946;

public class Solution {
    class DungeonInfo {
        int minimumFatigue;
        int consumeFatigue;

        public DungeonInfo(int minimumFatigue, int consumeFatigue) {
            this.minimumFatigue = minimumFatigue;
            this.consumeFatigue = consumeFatigue;
        }

        @Override
        public String toString() {
            return "DungeonInfo{" +
                    "minimumFatigue=" + minimumFatigue +
                    ", consumeFatigue=" + consumeFatigue +
                    '}';
        }
    }

    int max = 0;

    void calculate(DungeonInfo[] arr, int k) {
        int maxInner = 0;

        while(maxInner < arr.length) {
            if(k >= arr[maxInner].minimumFatigue) {
                k -= arr[maxInner].consumeFatigue;
            } else {
                break;
            }

            maxInner++;
        }

        max = Math.max(max, maxInner);
    }

    // 순서 없이 n 개중에서 r 개를 뽑는 경우
    // 사용 예시: permutation(arr, 0, n, 4);
    void permutation(DungeonInfo[] arr, int k, int depth, int n, int r) {
        if (depth == r) {
            calculate(arr, k);
            return;
        }

        for (int i=depth; i<n; i++) {
            swap(arr, depth, i);
            permutation(arr, k, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }

    static void swap(DungeonInfo[] arr, int depth, int i) {
        DungeonInfo temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }

    public int solution(int k, int[][] dungeons_temp) {
        DungeonInfo[] dungeons = new DungeonInfo[dungeons_temp.length];
        for(int i = 0; i < dungeons_temp.length; i++) {
            dungeons[i] = new DungeonInfo(dungeons_temp[i][0], dungeons_temp[i][1]);
        }

        permutation(dungeons, k, 0, dungeons.length, dungeons.length);

        return max;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {80, 20},
                {50, 40},
                {30, 10}
        };

        int k = 80;

        System.out.println(new Solution().solution(k, arr));
    }
}
