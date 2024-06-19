package kr.goldenmine.others.programmers.test_3.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public static boolean check(String user, String pattern) {
        if (user.length() != pattern.length()) return false;

        for (int i = 0; i < user.length(); i++) {
            char ch1 = user.charAt(i);
            char ch2 = pattern.charAt(i);

            if (ch2 == '*') continue;
            if (ch1 != ch2) return false;
        }
        return true;
    }

    int count = 0;
    HashSet<Integer> visits = new HashSet<>();

    int allVisitedBit = 0;

    public void dfs(String[] user_id, String[] banned_id, int visited, int visitedBanned, int count) {
//        System.out.println(visitedBanned + ", " + allVisitedBit);
        if (visitedBanned == allVisitedBit) {
            if (visits.contains(visited)) return;
            visits.add(visited);
            this.count++;
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            int visitedBit = 1 << i;
            if((visited & visitedBit) == 0) {
                visited |= visitedBit;
                int verify = -1;
                for (int j = 0; j < banned_id.length; j++) {
                    if (check(user_id[i], banned_id[j]) && (visitedBanned & (1 << j)) == 0) {
                        verify = j;
                        break;
                    }
                }
                if (verify != -1) {
                    int shift = 1 << verify;
                    visitedBanned |= shift;
                    dfs(user_id, banned_id, visited, visitedBanned, count + 1);
                    visitedBanned &= ~shift;
                }
                visited &= ~visitedBit;
            }
        }
    }

    public int solution(String[] user_id, String[] banned_id) {
        for(int i = 0; i < banned_id.length; i++) {
            allVisitedBit |= (1 << i);
        }

        dfs(user_id, banned_id, 0, 0, 0);
//        boolean[] banned = new boolean[user_id.length];

//        List<List<Integer>> filtered = new ArrayList<>();
//
//        for (int i = 0; i < banned_id.length; i++) {
//            filtered.add(new ArrayList<>());
//        }
//
//        for (int j = 0; j < banned_id.length; j++) {
//            for (int i = 0; i < user_id.length; i++) {
//                if (check(user_id[i], banned_id[j])) {
//                    filtered.get(j).add(i);
//                }
//            }
//        }
//
//        HashSet<Integer> alreadySelected = new HashSet<>();
//
//        for (int i = 0; i < filtered.size(); i++) {
//            int result = 0;
//            for (int j : filtered.get(i)) {
//
//            }
//        }


        return count;
    }

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};

        Solution solution = new Solution();


        System.out.println(solution.solution(user_id, banned_id));
//        System.out.println(solution.visits);
    }
}