package d230302.bj_17143;
/*
13:10 ~
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_1753_최단경로 {
    static class Edge implements Comparable<Edge>{
        int v, weight;
        public Edge(int v, int weight){
            this.v = v;
            this.weight =weight;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString(){
            return weight+"";
        }
    }
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine())-1;

        ArrayList<Edge>[] adf = new ArrayList[V];//인접 리스트 작성
        for(int i=0;i<V;i++){
            adf[i] = new ArrayList<>();
        }
        
        for(int i=0;i<E;i++){//리스트 값 할당
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            adf[from].add(new Edge(end,weight));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V];

        Edge[] D = new Edge[V];
        for(int i=0;i<V;i++){
            if(i==K){
                D[i] = new Edge(i,0);//시작노드면 0거리
                visited[i] = true;//방문처리
            }else{
                D[i] =new Edge(i,Integer.MAX_VALUE);//시작노드 아니면 무한거리
            }
            pq.add(D[i]);//pq에 넣는다
        }

        while(!pq.isEmpty()){
            Edge edge = pq.poll();//최초에 시작,0 나옴

            visited[edge.v] = true;
            if(edge.weight == Integer.MAX_VALUE){
                break;
            }

            for(Edge next:adf[edge.v]){//현재 엣지의 노드의 다른 엣지
                if(visited[next.v]) continue;
                if(D[next.v].weight > D[edge.v].weight+next.weight){
                    D[next.v].weight = D[edge.v].weight + next.weight;


                    //pq재정렬
                    pq.remove(D[next.v]);
                    pq.add(D[next.v]);
                }
            }
        }
        for(int i=0;i<V;i++){
            System.out.println(D[i].weight==Integer.MAX_VALUE?"INF":D[i].weight);
        }
    }

}
