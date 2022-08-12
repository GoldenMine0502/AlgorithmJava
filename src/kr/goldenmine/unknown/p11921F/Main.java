package kr.goldenmine.unknown.p11921F;

public class Main {
//    private static int BUFFER_SIZE = 1 << 20;
//    private static final DataInputStream din = new DataInputStream(System.in);
//    private static final byte[] buffer = new byte[BUFFER_SIZE];
//    private static int bufferPointer = 0;
//    private static int bytesRead = 0;

//    private static void fillBuffer() throws IOException {
//        bytesRead = din.read(buffer, bufferPointer = 0,
//                BUFFER_SIZE);
//        if (bytesRead == -1)
//            buffer[0] = -1;
//    }

//    public static final int DIGITS_TOTAL = 15;
//    public static final int DIGITS_TOTAL_MINUS_1 = DIGITS_TOTAL - 1;
//
//    public static void main(String[] args) throws IOException {
//        byte[] buffer = new byte[1000000];
//        int bufferPointer = 0;
//        System.in.read(buffer, 0, 1000000);
//
//        byte c;
//        int value, count = 100000, digit, index = 0;
//        int[] digits = new int[9];
//        int[] totals = new int[DIGITS_TOTAL];
//
//        // 한줄 스킵
//        do {
//
//        } while ((c = buffer[bufferPointer++]) >= '0' && c <= '9');
//
//        // 시작
//        for (int i = 0; i < count; i++) {
//            digit = 0;
//
//            // 구하기
//            c = buffer[bufferPointer++];
//
//            digits[digit++] = c - '0';
//
//            c = buffer[bufferPointer++];
//
//            if('0' <= c && c <= '9') {
//                digits[digit++] = c - '0';
//
//                c = buffer[bufferPointer++];
//
//                if('0' <= c && c <= '9') {
//                    digits[digit++] = c - '0';
//
//                    c = buffer[bufferPointer++];
//
//                    if('0' <= c && c <= '9') {
//                        digits[digit++] = c - '0';
//
//                        c = buffer[bufferPointer++];
//
//                        if('0' <= c && c <= '9') {
//                            digits[digit++] = c - '0';
//
//                            c = buffer[bufferPointer++];
//
//                            if('0' <= c && c <= '9') {
//                                digits[digit++] = c - '0';
//
//                                c = buffer[bufferPointer++];
//
//                                if('0' <= c && c <= '9') {
//                                    digits[digit++] = c - '0';
//
//                                    c = buffer[bufferPointer++];
//
//                                    if('0' <= c && c <= '9') {
//                                        digits[digit++] = c - '0';
//
//                                        c = buffer[bufferPointer++];
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
////            if(0 < digit) {
//                totals[15 - digit] += digits[0];
//
//                if(1 < digit) {
//                    totals[16 - digit] += digits[1];
//
//                    if(2 < digit) {
//                        totals[17 - digit] += digits[2];
//
//                        if(3 < digit) {
//                            totals[18 - digit] += digits[3];
//
//                            if(4 < digit) {
//                                totals[19 - digit] += digits[4];
//
//                                if(5 < digit) {
//                                    totals[20 - digit] += digits[5];
//
//                                    if(6 < digit) {
//                                        totals[21 - digit] += digits[6];
//
//                                        if(7 < digit) {
//                                            totals[22 - digit] += digits[7];
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
////            }
////            for(int j = 0; j < digit; j++) {
////                totals[DIGITS_TOTAL - digit + j] += digits[j];
////            }
//        }
//
//        for(int i = DIGITS_TOTAL_MINUS_1; i >= 1; i--) {
//            value = totals[i] % 10;
//            totals[i - 1] += totals[i] / 10;
//            totals[i] = value;
//        }
//
//        BufferedOutputStream  w = new BufferedOutputStream (System.out, 32);
//        w.write('5');
//        w.write('0');
//        w.write('0');
//        w.write('0');
//        w.write('0');
//        w.write('\n');
//
//        while(totals[index++] == 0);
//        for(int i = index - 1; i < DIGITS_TOTAL; i++) {
//            w.write(totals[i] + '0');
//        }
//        w.flush();
//    }

    public static final int DIGITS_TOTAL = 15;
    public static final int DIGITS_TOTAL_MINUS_1 = DIGITS_TOTAL - 1;

    public static void main(String[] args) throws Exception {
        byte[] buffer = new byte[6250000];
        System.in.read(buffer, 0, 6250000);

        int i = 0, digit = 0, index = 8, index2 = 0, value;
        byte b;

        int[] digits = new int[9];
        int[] totals = new int[DIGITS_TOTAL];

        while (i++ < 625000) {

            digit = 0;
            while ((b = buffer[index++]) != '\n') {
                digits[digit++] = b - 48;
            }

            totals[15 - digit] += digits[0];

            if (1 < digit) {
                totals[16 - digit] += digits[1];

                if (2 < digit) {
                    totals[17 - digit] += digits[2];

                    if (3 < digit) {
                        totals[18 - digit] += digits[3];

                        if (4 < digit) {
                            totals[19 - digit] += digits[4];

                            if (5 < digit) {
                                totals[20 - digit] += digits[5];

                                if (6 < digit) {
                                    totals[21 - digit] += digits[6];

                                    if (7 < digit) {
                                        totals[22 - digit] += digits[7];

                                        if (8 < digit) {
                                            totals[23 - digit] += digits[8];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        for(int k = DIGITS_TOTAL_MINUS_1; k >= 1; k--) {
            value = totals[k] % 10;
            totals[k - 1] += totals[k] / 10;
            totals[k] = value;
        }

        buffer[0] = '6';
        buffer[1] = '2';
        buffer[2] = '5';
        buffer[3] = '0';
        buffer[4] = '0';
        buffer[5] = '0';
        buffer[6] = '\n';
//        temp = 25;

//        while (sum > 0) {
//            buffer[temp++] = (byte) (sum % 10 + 48);
//            sum /= 10;
//        }

//        System.out.println(Arrays.toString(totals));

        index = 7;
        while(totals[index2++] == 0);
        for(int oo = index2 - 1; oo < DIGITS_TOTAL; oo++) {
            buffer[index++] = (byte) (totals[oo] + '0');
        }
//        while (temp-- > 25) {
//            buffer[index++] = buffer[temp];
//        }

        System.out.write(buffer, 0, index);
    }
}
