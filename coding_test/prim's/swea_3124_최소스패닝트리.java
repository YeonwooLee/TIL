package d230227.swea_최소스패닝트리;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class swea_3124_최소스패닝트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long sumWeight;
    static boolean[] visited;
    static ArrayList<Edge>[] vertex;

    public static void main(String[] args) throws IOException {

        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t;tc++){
            System.out.printf("#%d ",tc);
            cal();
        }

    }
    static void cal() throws IOException{
        sumWeight = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        vertex = new ArrayList[V+1];
        visited = new boolean[V+1];
        for(int i=1;i<=V;i++){
            vertex[i] = new ArrayList<>();
        }
        int E = Integer.parseInt(st.nextToken());
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            vertex[s].add(new Edge(e,w));
            vertex[e].add(new Edge(s,w));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        pq.offer(new Edge(1,0));
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            if(visited[cur.e]) continue;

            visited[cur.e] = true;
            sumWeight += cur.w;

            for(Edge e:vertex[cur.e]){
                int ne = e.e;
                int nw = e.w;

                if(visited[ne]) continue;
                pq.offer(new Edge(ne,nw));
            }

        }
        System.out.println(sumWeight);
    }
    static class Edge implements Comparable<Edge>{
        int e,w;
        public Edge(int e, int w){
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o){
            return this.w-o.w;
        }
    }
}
