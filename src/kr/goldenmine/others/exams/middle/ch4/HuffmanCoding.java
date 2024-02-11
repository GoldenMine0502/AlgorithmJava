package kr.goldenmine.others.exams.middle.ch4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCoding {
    static class Node {
        char text;
        int weight;
        Node left;
        Node right;

        public Node(char text, int weight) {
            this.text = text;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "text=" + text +
                    ", weight=" + weight +
                    (left != null ? ", \nleft=" + left : "") +
                    (right != null ? ", \nright=" + right : "") +
                    '}';
        }
    }

    // Build Huffman Tree Recursive method
    public static Node buildTree(PriorityQueue<Node> priorityQueue){
        if(priorityQueue.size() == 1){
            return priorityQueue.poll();
        }else{
            Node leftNode = priorityQueue.poll();
            Node rightNode = priorityQueue.poll();

            Node sumNode = new Node(' ', leftNode.weight + rightNode.weight);
            sumNode.left = leftNode;
            sumNode.right = rightNode;

            priorityQueue.offer(sumNode);
            return buildTree(priorityQueue);
        }
    }

    public static void main(String[] args) {
        // A: 450 T: 90 G: 120 C: 270
        Node[] nodes = {
                new Node('A', 4),
                new Node('C', 1),
                new Node('T', 3),
                new Node('D', 1),
                new Node('W', 1),
                new Node('K', 1),
                new Node('N', 1),
        };

        // Line 2를 수행한 후의 Q -> 우선순위 큐 Q를 생성
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(o -> o.weight));
        priorityQueue.addAll(Arrays.asList(nodes));

        System.out.println(priorityQueue);

        Node result = buildTree(priorityQueue);
        System.out.println(result);
    }
}
