package kr.goldenmine.others.exams.middle.ch4;

import java.util.*;

public class SetCovering {

    /*
    U={1, 2, 3, 4, 5, 6, 7, 8, 9, 10} //10towns
    F = {S1, S2, S3, S4, S5, S6, S7, S8, S9, S10}
    S1={1, 2, 3, 8}
    S2={1, 2, 3, 4, 8}
    S3={1, 2, 3, 4}
    S4={2, 3, 4, 5, 7, 8}
    S5={4, 5, 6, 7}
    S6={5, 6, 7, 9, 10}
    S7={4, 5, 6, 7}
    S8={1, 2, 4, 8}
    S9={6, 9}
    S10={6, 10}
     */

    static class Node {
        String name;
        Set<Integer> set;

        public Node(String name, Set<Integer> set) {
            this.name = name;
            this.set = set;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", set=" + set +
                    '}';
        }
    }

    public static void main(String[] args) {
        Set<Integer> U = new HashSet<>(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Node S1 = new Node("S1", new HashSet<>(Set.of(1, 2, 3, 8)));
        Node S2 = new Node("S2", new HashSet<>(Set.of(1, 2, 3, 4, 8)));
        Node S3 = new Node("S3", new HashSet<>(Set.of(1, 2, 3, 4)));
        Node S4 = new Node("S4", new HashSet<>(Set.of(2, 3, 4, 5, 7, 8)));
        Node S5 = new Node("S5", new HashSet<>(Set.of(4, 5, 6, 7)));
        Node S6 = new Node("S6", new HashSet<>(Set.of(5, 6, 7, 9, 10)));
        Node S7 = new Node("S7", new HashSet<>(Set.of(4, 5, 6, 7)));
        Node S8 = new Node("S8", new HashSet<>(Set.of(1, 2, 4, 8)));
        Node S9 = new Node("S9", new HashSet<>(Set.of(6, 9)));
        Node S10= new Node("S10", new HashSet<>(Set.of(6, 10)));

        List<Node> F = new ArrayList<>(List.of(S1, S2, S3, S4, S5, S6, S7, S8, S9, S10));

        List<String> results = new ArrayList<>();

        System.out.println("start: " + U);
        while(U.size() > 0) {
            F.sort(Comparator.comparingInt(o -> o.set.size()));

            Node node = F.remove(F.size() - 1);

            System.out.println("selected: " + node);

            // adding name
            results.add(node.name);

            // removing nodes
            U.removeAll(node.set);
            for(Node next : F) {
                next.set.removeAll(node.set);
            }
        }

        System.out.println(results);
    }
}
