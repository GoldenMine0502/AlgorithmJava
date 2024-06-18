package kr.goldenmine.baekjoon.diamond.diamond5.p13705;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.StringTokenizer;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static final BigDecimal ZERO = new BigDecimal("0");
    static final BigDecimal ONE = new BigDecimal("1");
    static final BigDecimal MINUS_ONE = new BigDecimal("-1");
    static final BigDecimal TWO = new BigDecimal("2");

    static final int ROUND_COUNT = 60;
    static final BigDecimal LOOP_COUNT = new BigDecimal(String.valueOf(ROUND_COUNT));
    static final BigDecimal ERROR = new BigDecimal(
            ("0.0000000000000000000000000000000000000000000000000" +
                 "00000000000000000000000000000000000000000000000000")
                    .substring(0, ROUND_COUNT + 2 - 20) + "1");

    static final BigDecimal PI = new BigDecimal(
            ("3.14159265358979323846264338327950288419716939937510" +
            "58209749445923078164062862089986280348253421170679" //+
//            "82148086513282306647093844609550582231725359408128" //+
//            "48111745028410270193852110555964462294895493038196"// +
//            "44288109756659334461284756482337867831652712019091" +
//            "45648566923460348610454326648213393607260249141273" +
//            "72458700660631558817488152092096282925409171536436" +
//            "78925903600113305305488204665213841469519415116094" +
//            "33057270365759591953092186117381932611793105118548" +
//            "07446237996274956735188575272489122793818301194912" +
//            "98336733624406566430860213949463952247371907021798" +
//            "60943702770539217176293176752384674818467669405132" +
//            "00056812714526356082778577134275778960917363717872" +
//            "14684409012249534301465495853710507922796892589235" +
//            "42019956112129021960864034418159813629774771309960" +
//            "51870721134999999837297804995105973173281609631859" +
//            "50244594553469083026425223082533446850352619311881" +
//            "71010003137838752886587533208381420617177669147303" +
//            "59825349042875546873115956286388235378759375195778" +
//            "18577805321712268066130019278766111959092164201989"
            ).substring(0, ROUND_COUNT + 2));

    static BigDecimal boundTwoPI(BigDecimal x) {
        // x / (2*PI)에 대한 몫
        BigDecimal twoPI = TWO.multiply(PI, MathContext.UNLIMITED);
        BigDecimal div = x.divide(twoPI, ROUND_COUNT, RoundingMode.HALF_UP).setScale(0, RoundingMode.DOWN);
        BigDecimal toSub = div.multiply(twoPI, MathContext.UNLIMITED);
        return x.subtract(toSub);
    }

    static BigDecimal multiply128(BigDecimal a, BigDecimal b) {
        return a.multiply(b, MathContext.DECIMAL128);
    }

    static BigDecimal sin (BigDecimal x){
        x = boundTwoPI(x);
//        System.out.println(x);
        BigDecimal i    = ONE;
        BigDecimal cur  = x;
        BigDecimal acc  = ONE;
        BigDecimal fact = ONE;
        BigDecimal pow  = x;

        while (i.compareTo(LOOP_COUNT) < 0){
            fact = fact.multiply(TWO.multiply(i, MathContext.UNLIMITED).multiply((TWO.multiply(i, MathContext.UNLIMITED).add(ONE)), MathContext.UNLIMITED), MathContext.UNLIMITED);
            pow = pow.multiply(MINUS_ONE.multiply(x, MathContext.UNLIMITED).multiply(x, MathContext.UNLIMITED), MathContext.UNLIMITED);
            acc = pow.divide(fact, ROUND_COUNT, RoundingMode.HALF_DOWN);
            cur = cur.add(acc);
            i = i.add(ONE);
        }

        return cur;
    }


    public static void main(String[] args) {
//        System.out.println(sin(new BigDecimal("-10")));
//        System.out.println(Math.sin(-10));

//        System.out.println(ERROR);
//        System.out.println(PI);

        FastReader scan = new FastReader();

        BigDecimal A = new BigDecimal(String.valueOf(scan.nextInt()));
        BigDecimal B = new BigDecimal(String.valueOf(scan.nextInt()));
        BigDecimal C = new BigDecimal(String.valueOf(scan.nextInt()));

        BigDecimal xmin = new BigDecimal("0"); // 1 * 1000 - 1000
        BigDecimal xmax = new BigDecimal("100000");

        while(xmin.compareTo(xmax) < 0) {
            BigDecimal mid = (xmin.add(xmax)).divide(TWO, ROUND_COUNT, RoundingMode.HALF_UP);

            // B ≤ A 이므로 증가함수다.
            BigDecimal left = A.multiply(mid, MathContext.UNLIMITED).add(B.multiply(sin(mid), MathContext.UNLIMITED));
            BigDecimal right = C;

//            System.out.println(xmin.doubleValue() + ", " + xmax.doubleValue() + ", " + left.doubleValue());
//            System.out.println(mid);

            BigDecimal sub = right.subtract(left);

            if(sub.abs().compareTo(ERROR) < 0) {
                System.out.println(mid.setScale(6, RoundingMode.HALF_UP));
                break;
            }

            if(sub.compareTo(ZERO) > 0) { // 오른쪽이 더 크면 왼쪽이 더 작다는 뜻. left가 커져야 하므로
                xmin = mid;
            } else {
                xmax = mid;
            }
        }
    }
}
