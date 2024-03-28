package kr.goldenmine.my;

import java.util.*;
import java.util.stream.Collectors;

public class AdvancedProgrammingLanguage1 {
    static class Node {
        String str;
        int depth;

        Node(String str, int depth) {
            this.str = str;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // S → AaBb
        // A → Ab | b
        // B → aB | a
        HashMap<String, String> nexts = new HashMap<>();
        nexts.put("S", "(L) | a");
        nexts.put("L", "L, S | S");
//        nexts.put("S", "AaBb");
//        nexts.put("A", "Ab | b");
//        nexts.put("B", "aB | a");

        List<String> results = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node("S", 0));

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            int count = 0;
            for(Map.Entry<String, String> expressions : nexts.entrySet()) {
//                List<String> values = Arrays.stream(expressions.getValue().split(" \\| "))
//                        .sorted(Comparator.comparingInt(String::length))
//                        .collect(Collectors.toList());
                String[] values = expressions.getValue().split(" \\| ");
                for(String value : values) {
                    if(current.str.contains(expressions.getKey())) {
                        String res = current.str.replaceFirst(expressions.getKey(), value);
//                        if(results.contains(res)) continue;
                        queue.add(new Node(res, current.depth + 1));
                        count++;
                    }
                }
            }
            if(count == 0) { // ended
                if(!results.contains(current.str))
                    results.add(current.str);
            }
            if(results.size() >= 100) {
                break;
            }
        }

        for(String res : results) {
            System.out.println(res);
        }
//        System.out.println(results);
    }
}
