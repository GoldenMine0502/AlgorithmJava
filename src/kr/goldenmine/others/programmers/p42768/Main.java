package kr.goldenmine.others.programmers.p42768;

import java.util.Arrays;

public class Main {

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

//        Arrays.sort(answer);

        for(int T = 0; T < commands.length; T++) {
            int a = commands[T][0];
            int b = commands[T][1];

            int[] newArray = new int[b - a + 1];
            for(int i = a - 1; i <= b - 1; i++) {
                newArray[i - a + 1] = array[i];
            }

            Arrays.sort(newArray);

            int c = commands[T][2];

            answer[T] = newArray[c - 1];
        }
        return answer;
    }

    public static void main(String[] args) {

    }
}
