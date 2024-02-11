package kr.goldenmine.baekjoon.gold.gold1.p3665A_topology_swap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int TC, N, M, inDegree[];
    static boolean arr[][]; // 노드 관계를 저장할 인접행렬
    static Queue<Integer> queue;
    static StringBuilder ans = new StringBuilder(), sb;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        while(TC-->0) {
            queue = new LinkedList<>();
            N = Integer.parseInt(br.readLine());
            inDegree = new int[N+1];
            arr = new boolean[N+1][N+1];
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<N;i++) {
                int now = Integer.parseInt(st.nextToken());
                inDegree[now] = i; // 진입 차수는 등수대로
                for (int j=1;j<=N;j++) {
                    // 관련 간선이 없다면 만들기
                    if (j!=now && !arr[j][now]) arr[now][j] = true;
                }
            }

            M = Integer.parseInt(br.readLine());
            for (int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                swap(from, to); // 순위 변경
            }

            ans.append(topology()+"\n");
        }
        System.out.println(ans);
    }

    static void swap(int from, int to) {
        // from->to가 false다 == from보다 to순위가 더 낮음 -> 높아져야됨
        if (!arr[from][to]) {
            arr[from][to] = true;
            arr[to][from] = false;
            inDegree[from]--;
            inDegree[to]++;
        }
        // from->to가 true다 == from보다 to순위가 더 높음 -> 낮아져야됨
        else {
            arr[from][to] = false;
            arr[to][from] = true;
            inDegree[from]++;
            inDegree[to]--;
        }
    }

    static String topology() {
        sb = new StringBuilder();
        for (int i=1;i<=N;i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        for (int i=1;i<=N;i++) {
            // 사이클 발생 IMPOSSIBLE
            if (queue.isEmpty()) return "IMPOSSIBLE";
                // 이동할 수 있는 정점이 여러 개 == 나올 수 있는 최종 순위가 여러 개
            else if (queue.size()>1) return "?";

            int now = queue.poll();
            sb.append(now+" ");

            for (int j=1;j<=N;j++) {
                if (arr[now][j]) { // 이동할 수 있는 정점
                    arr[now][j] = false; // 이동했다!
                    // 진입 차수 줄여주고 0이면 이동
                    if (--inDegree[j] == 0) queue.add(j);
                }
            }
        }
        return sb.toString();
    }
}
