package kr.goldenmine;

import java.util.ArrayList;
import java.util.List;

public class KMP {
    public static int[] getFailure(String key) {
        int[] pi = new int[key.length()];
        pi[0] = 0;

        int j = 0;

        for(int i = 1; i < key.length(); i++) {
            while(j > 0 && key.charAt(i) != key.charAt(j))
                j = pi[j - 1];

            if(key.charAt(i) == key.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    public static List<Integer> kmp(String text, String key) {
        List<Integer> indices = new ArrayList<>();

        int[] pi = getFailure(key);

        int j = 0;

        for(int i = 0; i < text.length(); i++) {
            while(j > 0 && text.charAt(i) != key.charAt(j))
                j = pi[j - 1];

            if(text.charAt(i) == key.charAt(j)) {
                if(j == key.length() - 1) {
                    indices.add(i - key.length() + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        return indices;
    }
}
