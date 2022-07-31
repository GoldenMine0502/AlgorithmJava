package kr.goldenmine.programmers.p42746;

import java.sql.Array;
import java.util.*;

public class Main {
    static class Data {
        String value;

        public Data(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }

    public static String solution(int[] numbers) {
        int N = numbers.length;

        Data[] priority = new Data[N];

        for(int i = 0; i < N; i++) {
            String number = String.valueOf(numbers[i]);

//            List<Integer> digits = new LinkedList<>();
//            do {
//                digits.add(0, numbers[i] % 10);
//                numbers[i] /= 10;
//            } while(numbers[i] > 0);

            priority[i] = new Data(number);
        }

        Arrays.sort(priority, (o1, o2) -> {
            int max = Math.min(o1.value.length(), o2.value.length());

            for (int i = 0; i < max; i++) {
                int o1v = o1.value.charAt(i) - '0';
                int o2v = o2.value.charAt(i) - '0';

                if (o1v < o2v) return 1;
                if (o1v > o2v) return -1;
            }

            // 작은 길이 기준으론 똑같음
            if(o1.value.length() < o2.value.length()) {
//                System.out.println("a");
                return o1.value.charAt(max - 1) - o2.value.charAt(max);
            } else if(o1.value.length() > o2.value.length()){
//                System.out.println("b" + ", " + o1.digits.get(max) + ", " + o2.digits.get(0));
                return o2.value.charAt(max - 1) - o1.value.charAt(max);
            } else {
                return 0;
            }
        });

        for(int i = 0; i < priority.length; i++) {
            System.out.println(priority[i]);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < priority.length; i++) {
            sb.append(priority[i].value);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {3, 30, 34, 5, 9, 1, 2, 12314, 300, 23, 1293};

        String result = solution(arr);
        System.out.println(result);
    }
}
