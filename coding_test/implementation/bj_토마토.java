package d0303.bj_도마도;

import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.io.*;
import java.util.StringTokenizer;
/*
입력
n,m
map[][]
안익은도마도갯수
익은도마도위치큐

도마도하루지내기



 */
public class Main {
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int notYet = 0;
    static int n,m;
    static int[][] map;
    static boolean[][] visited;
    static int day = 0;
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                int cur = Integer.parseInt(st.nextToken());
                map[i][j] = cur;
                if(cur==1){//익토
                    list.add(new int[]{i,j});
                }else if(cur==0){//안익토
                    notYet++;//안익토수
                }
            }
        }

        boolean res = goDay();
        if(res){
            System.out.println(day);
        }else{
            System.out.println(-1);
        }
    }
    static boolean goDay(){
        day=0;
        if(notYet==0) return true;
        //현재 익토만큼 반복

        int size = list.size();
        Queue<int[]> q = new ArrayDeque<>();
        for(int i=0;i<size;i++){
            int[] curIkTo = list.get(i);
            int curIkToR = curIkTo[0];
            int curIkToC = curIkTo[1];
            q.offer(curIkTo);
            visited[curIkToR][curIkToC] = true;

        }
        int turn = -1;
        while(!q.isEmpty()){
            turn++;
            int qsize = q.size();
            while(qsize-->0){
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                for(int i=0;i<4;i++){
                    int nr = r+dr[i];
                    int nc = c+dc[i];

                    if(!isIn(nr,nc)) continue;
                    if(visited[nr][nc]) continue;
                    if(map[nr][nc]!=0) continue;

                    map[nr][nc]=1;
                    notYet--;
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr,nc});
                }

            }
        }
        day = turn;
        return notYet==0?true:false;


    }
    static boolean isIn(int r, int c){
//        System.out.println("n="+n);
//        System.out.println("nm="+m);
        boolean result = r>=0 && r<n && c>=0 && c<m;
//        if(result) System.out.println("내부");
//        else System.out.println("외부");
        return result;
    }

    static void printArr(){
        System.out.println();

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j]+ " ");

            }
            System.out.println();
        }
        System.out.println();
    }
}
