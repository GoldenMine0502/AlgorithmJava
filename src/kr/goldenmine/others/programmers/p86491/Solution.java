package kr.goldenmine.others.programmers.p86491;

public class Solution {
    public int solution(int[][] sizes) {
        int xSizeMax = 0;
        int ySizeMax = 0;

        for(int i = 0; i < sizes.length; i++) { // 한쪽만 크도록 스왑
            if(sizes[i][0] > sizes[i][1]) {
                int temp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = temp;
            }
            System.out.println(sizes[i][0] + ", " + sizes[i][1]);
        }

        for(int i = 0; i < sizes.length; i++) {
            xSizeMax = Math.max(xSizeMax, sizes[i][0]);
            ySizeMax = Math.max(ySizeMax, sizes[i][1]);
        }
        return xSizeMax * ySizeMax;
    }
}