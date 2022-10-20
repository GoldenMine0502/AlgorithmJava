package kr.goldenmine.exams.digits;

public class Digits16to2 {
    public static void main(String[] args) throws InterruptedException {
        int count = 6;
        int src = 0x1234ef;

        String[] digits = {
                "0000", // 0
                "0001", // 1
                "0010", // 2
                "0011", // 3
                "0100", // 4
                "0101",
                "0110",
                "0111",
                "1000", // 8
                "1001",
                "1010",
                "1011",
                "1100", // 12
                "1101", // 13
                "1110", // 14
                "1111", // 15
        };

        String digit = "";
        for(int i = count - 1; i >= 0; i--) {
            int mask = 0x0000000F << (i << 2);
            int result = (src & mask) >> (i << 2);

            digit += digits[result];
        }
        System.out.println(digit);
    }
}
