package kr.goldenmine.programmers.p84512;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public int solution(String word) {
        char[] alphabets = {'A', 'E', 'I', 'O', 'U', ' '};

        HashMap<Character, Integer> characters = new HashMap<>();
        characters.put('A', 0);
        characters.put('E', 1);
        characters.put('I', 2);
        characters.put('O', 3);
        characters.put('U', 4);

        char[] arr = {' ', ' ', ' ', ' ', ' '};

        int count = 0;

        while(true) {
            // 없는경우
            boolean added = false;

            // 비어있는 곳 있으면 새로 채우기
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] == ' ') {
                    arr[i] = alphabets[0];
                    added = true;
                    break;
                }
            }

            // 다 있는경우
            if(!added) {
                int index = 5;

                while(--index >= 0) {
                    if(arr[index] != ' ') {
                        // 다음 알파벳으로 교체, A -> E, E -> I...
                        arr[index] = alphabets[characters.get(arr[index]) + 1];

                        // 받아올림, == 인 경우 위 알파벳도 인덱스 1 올려준다.
                        if(arr[index] != ' ') {
                            break;
                        }
                    }
                }
            }

            // 횟수 1회 증가. 사전순 번호가 된다.
            count++;

            // 동일 여부 체크
            boolean equals = true;

            for(int i = 0; i < 5; i++) {
                char wordCh = i < word.length() ? word.charAt(i) : ' ';
                if(arr[i] != wordCh) {
                    equals = false;
                }
            }

            if(equals) break;
        }

        /*
        1 A''''
        2 AA'''
        3 AAA''
        4 AAAA'
        5 AAAAA
        6 AAAAE
        7 AAAAI
        8 AAAAO
        9 AAAAU
        10 AAAE'
        11 AAAEA
        ..

        1562 EUUUU
        1563 I


         */

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("AAAAE"));
        System.out.println(new Solution().solution("AAAE"));
        System.out.println(new Solution().solution("I"));
        System.out.println(new Solution().solution("EIO"));
    }
}
