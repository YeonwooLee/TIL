package d230227.swea_최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Swea_3124_최소스패닝트리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] parent;
    static long result;
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++){
            System.out.printf("#%d ",tc);
            cal();
        }
    }
    static void cal() throws IOException{
        result = 0;
        StringTokenizer st;
        st= new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        parent  = new int[V+1];
        for(int i=1;i<=V;i++){
            parent[i] = i;

        }
        int E = Integer.parseInt(st.nextToken());
        Edge[] edgeArr = new Edge[E];

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int s =Integer.parseInt(st.nextToken());
            int e =Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edgeArr[i] = new Edge(s,e,w);
        }

        Arrays.sort(edgeArr);
        for(int i=0;i<E;i++){
            Edge curEdge = edgeArr[i];
            int curA = curEdge.s;
            int curE = curEdge.e;
            int curWeight = curEdge.w;

            boolean isGroup = isGroup(curA,curE);
            if(isGroup) continue;


            union(curA,curE);
            result += curWeight;
        }
        System.out.println(result);

    }
    static void union(int a, int b){
        int aP = findParent(a);
        int bP = findParent(b);
        if(aP<bP){
            parent[bP] = aP;
        }else{
            parent[aP] = bP;
        }
    }
    static boolean isGroup(int a, int b){
        int ap = findParent(a);
        int bp = findParent(b);
        return ap == bp;
    }
    static int findParent(int a){
        int p = parent[a];
        if(p==a) return p;

        int pp = findParent(p);
        parent[a] = pp;
        return pp;
    }
    static class Edge implements Comparable<Edge>{
        @Override
        public int compareTo(Edge o){
            return this.w-o.w;
        }
        public Edge(int s, int e, int w){
            this.s= s;
            this.e = e;
            this.w = w;
        }
        int s,e,w;
    }

}
