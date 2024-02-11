package kr.goldenmine.others.programmers.course30.p2_118667;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int[] queue1_temp, int[] queue2_temp) {
        long queue1Sum = 0;
        long queue2Sum = 0;

        Queue<Integer> queue1 = new LinkedList<>();
        for(int i = 0; i < queue1_temp.length; i++) {
            queue1.add(queue1_temp[i]);

            queue1Sum += queue1_temp[i];
        }

        Queue<Integer> queue2 = new LinkedList<>();
        for(int i = 0; i < queue2_temp.length; i++) {
            queue2.add(queue2_temp[i]);

            queue2Sum += queue2_temp[i];
        }

        int count = 0;
        while(queue1Sum != queue2Sum) {
            if(queue1Sum > queue2Sum) { // 이 경우 1에 있는걸 2로 옮겨 밸런스 맞추기 시도
                if(queue1.size() > 1) { // 큐가 비어버린다는건 안됨
                    int poll = queue1.poll();

                    queue1Sum -= poll;
                    queue2Sum += poll;

                    queue2.add(poll);
                } else {
                    count = -1;
                    break;
                }
            } else {
                if(queue2.size() > 1) { // 큐가 비어버린다는건 안됨
                    int poll = queue2.poll();

                    queue2Sum -= poll;
                    queue1Sum += poll;

                    queue1.add(poll);
                } else {
                    count = -1;
                    break;
                }
            }
            count++;

            if(count >= 600000) { // 60만번이면 모든 큐가 한번씩 모든 값을 다 뺐을 타이밍
                count = -1;
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) {

    }
}
