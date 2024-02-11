package kr.goldenmine.others.wooteco.p1;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<List<String>> profiles = Arrays.asList(
                Arrays.asList("jm@email.com", "제이엠"),
                Arrays.asList("jason@email.com", "제이슨"),
                Arrays.asList("woniee@email.com", "워니"),
                Arrays.asList("mj@email.com", "엠제이"),
                Arrays.asList("nowm@email.com", "이제엠")
        );

        Set<Integer> indices = new HashSet<>();

        for(int i = 0; i < profiles.size(); i++) {
            String email = profiles.get(i).get(0);
            String nickname = profiles.get(i).get(1);

            for(int j = 0; j < profiles.size(); j++) {
                if(i != j) {
                    String nickname2 = profiles.get(j).get(1);

                    for(int k = 0; k < nickname.length() - 1; k++) {
                        String small = nickname.substring(k, k + 2);

                        if(nickname2.contains(small)) {
                            indices.add(i);
                            indices.add(j);

                            break;
                        }
                    }
                }
            }
        }

        List<String> emails = indices.stream().map(it -> profiles.get(it).get(0)).sorted(String::compareTo).collect(Collectors.toList());

        System.out.println(emails);
    }
}
