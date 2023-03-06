package d0303.bj_아기상어;
/*
NN배열
M마리 물고기
상어 1마리(시작크기 = 2, 매초 1칸씩 4방이동)
    상어특
        자기보다 작거나 같은 물고기칸 이동 가능
        자기보다 작은 물고기만 먹기 가능
        먹은물고기갯수 == 자기크기 -> 크기++
    상어 이동
        가장 가까운 물고기를 먹는다
            가장 가까운?
                -r이가장 작은
                -c가 가장 작은
        물괴기 없으면 엄마콜

칸당 물고기 최대 1마리


상어가 엄마 부르는 시간

0. 맵 구성
1. 먹을 물고기 탐색
2. 이동(맨하탄거리)
3. 먹기
 */
import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    static int n;
    static int[][] fishMap;
    static boolean[][] visited;
    static Shark s;
    static class Shark{
        int r,c,size;
        int eaten;
        public Shark(int r,int c, int size){
            this.r = r;
            this.c = c;
            this.size = size;
            this.eaten = 0;
        }
    }

    static int resultTime = 0;
    public static void main(String[] args) throws IOException {
        setting();
        while(true){
            int[] fishPositionWithDist = findNearFish();
            int fishR = fishPositionWithDist[0];
            int fishC = fishPositionWithDist[1];
            int dist = fishPositionWithDist[2];
            if(dist==-1){
                break;
            }

            s.r = fishR;
            s.c = fishC;
            s.eaten++;
            if(s.eaten==s.size){
                s.size++;
                s.eaten=0;
            }
            resultTime += dist;

            fishMap[s.r][s.c] = 0;
        }
        System.out.println(resultTime);
    }
    static void setting() throws IOException{
        n = Integer.parseInt(br.readLine());
        fishMap = new int[n][n];
        visited = new boolean[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                int cur = Integer.parseInt(st.nextToken());
                if(cur!=9) fishMap[i][j] =cur;
                else{
                    s = new Shark(i,j,2);
                }
            }
        }
    }
    static int[] findNearFish(){
        int sharkR = s.r;
        int sharkC = s.c;
//        System.out.printf("상어위치 %d,%d\n",sharkR,sharkC);
        int[] res = bfs(sharkR,sharkC);
//        System.out.printf("가까운 문어 %d,%d 찾은턴 = %d, 문어 = %d\n",res[0],res[1],res[2],fishMap[res[0]][res[1]]);
        return res;
    }

    static int[] bfs(int sr, int sc){
        //visited 초기화
        for(int i=0;i<n;i++){
            Arrays.fill(visited[i],false);
        }
        //ㅋ 생성
        Queue<int[]> q = new ArrayDeque<>();
        //초기좌표설정
        q.offer(new int[]{sr,sc});
        visited[sr][sc] = true;

        int turn = -1;
        int size;
        boolean continueFlag = false;
        int[] res = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,-1};//물고기r,물고기c,먹는턴

        while(!q.isEmpty()){
            turn++;
            size = q.size();
            while(size-->0){
                int[] cur =q.poll();//이번턴의 물고기뽑
                int cr = cur[0];
                int cc = cur[1];

                //가장 가까운 먹을 수 있는 물고기 발견
                if(fishMap[cr][cc]!=0 && fishMap[cr][cc]<s.size){
                    res[2]=turn;
                    continueFlag = true;
                    if(cr<res[0]){
                        res[0] =  cr;
                        res[1] = cc;
//                        System.out.printf("물고기발견 %d,%d\n",res[0],res[1]);
                    }if(cr==res[0] && cc<res[1]){
                        res[0] =cr;
                        res[1] = cc;
//                        System.out.printf("물고기발견 %d,%d\n",res[0],res[1]);
                    }

                }
                if(continueFlag) continue;//다음턴 넘어갈 이유 없음
                for(int i=0;i<4;i++){
                    int nr = cr+dr[i];
                    int nc = cc+dc[i];
                    if(!isIn(nr,nc)) continue;//범위초과
                    if(visited[nr][nc]) continue;//방문초과
                    if(fishMap[nr][nc]>s.size) continue;//상어보다 큰물고기

                    visited[nr][nc] = true;//방문처리
                    q.offer(new int[]{nr,nc});//오퍼
                }

            }
            if(continueFlag){
                break;//없어도 될듯
            }

        }
        return res;
    }
    static boolean isIn(int r, int c){
        return r>=0 && r<n && c>=0 && c<n;
    }
}
