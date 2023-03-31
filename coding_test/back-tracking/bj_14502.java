package bj_14502;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] pick = new int[3][2];

    static int N;
    static int M;

    static int[][] map;
    static int[][] temp;

    static int result = Integer.MIN_VALUE;

    static List<int[]> virusList = new ArrayList<>();
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    public static void main(String[] args) throws IOException{

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        temp = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==2){
                    virusList.add(new int[]{i,j});
                }
            }
        }

        //r,c,cnt,map

        dfs(0,0,0);
        System.out.println(result);
    }
    static void dfs(int r,int c, int cnt){

        if(cnt==3){
//            printPick();
            check();
            return;
        }
        if(r==-1){
            return;
        }
        int[] nrnc = getNrNc(r,c);
        int nr = nrnc[0];
        int nc = nrnc[1];
        //현재칸 벽 안 세움
        dfs(nr,nc,cnt);

        //벽 세울 수 있으면 벽세운 분기 추가
        if(map[r][c]==0){
            //cnt번째 벽위치 설정
            pick[cnt][0] = r;
            pick[cnt][1] = c;

            //현칸 사용
            dfs(nr,nc,cnt+1);
        }


    }


    static int[] getNrNc(int r, int c){
        c+=1;
        if(c==M){
            c=0;
            r++;
            if(r==N) r=-1;
        }
        return new int[]{r,c};
    }

    static void check(){
        for(int i=0;i<N;i++){
            temp[i] = map[i].clone();
        }
        //벽을 세우다
        for(int i=0;i<pick.length;i++){
            int pr = pick[i][0];
            int pc = pick[i][1];

            temp[pr][pc] = 1;
        }
        bfs();
    }
    static void bfs(){
//        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        //q에 바이러스위치 추가, 방문처리
        for(int[] cur:virusList){
            int curR = cur[0];
            int curC = cur[1];
            q.offer(new int[]{curR,curC});
//            visited[curR][curC] = true;
        }
        int turn = -1;
        while(!q.isEmpty()){
            int size = q.size();
            turn++;
            while(size-->0){
                int[] cur = q.poll();
                int curR = cur[0];
                int curC = cur[1];
                for(int i=0;i<4;i++){
                    int nr = curR+dr[i];
                    int nc = curC+dc[i];

                    if(nr<0||nr>=N||nc<0||nc>=M) continue;
//                    if(visited[nr][nc]) continue;
                    if(temp[nr][nc]==0){
                        temp[nr][nc]=2;
                        q.offer(new int[]{nr,nc});
                    }
                }
            }
        }
        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(temp[i][j]==0){
                    cnt++;
                }
            }
        }
        result = Math.max(cnt,result);
    }
    static void printArr(int[][] mpa){
        System.out.println();
        for(int i=0;i<mpa.length;i++){
            for(int j=0;j<mpa[0].length;j++){
                System.out.printf("%2d",mpa[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printPick(){
        for(int i=0;i<pick.length;i++){
            System.out.printf("(%d,%d) ",pick[i][0],pick[i][1]);
        }
        System.out.println();
    }

}
