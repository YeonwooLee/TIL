import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
사무실 입구는 배열에 1씩 마진을 줘서 찾는다

0,0에서 bfs탐색
문을 찾았을 때
    열쇠가 있으면 bfs탐색
    열쇠가 없으면 가능성에 담아둔다
열쇠를 찾았을 때
    가능성 리스트를 담는다
 */
public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};


    static char[][] map;//기지
    static boolean[] key;//열쇠 보유
    static ArrayList<int[]>[] oddsList;//가능성 리스트

    static int h,w;//높이, 너비
    static int T;//테케 수

    public static void main(String[] args) throws IOException{
        //알파벳 개수 찾기(26개)
        //97 122 65 90
        //System.out.printf("%d %d %d %d",(int)'a',(int)'z',(int)'A',(int)'Z');

        T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++){
            cal();
        }
        bw.close();
    }
    static void cal() throws IOException{
        //보유중 키 초기화
        key = new boolean[26];

        //가능성 초기화
        oddsList = new ArrayList[26];
        for(int i=0;i<oddsList.length;i++){
            oddsList[i] = new ArrayList<>();
        }

        //높이와 너비
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        //사무실 초기화
        map = new char[h+2][w+2];
        //배경표현
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                map[i][j] = '.';
            }
        }

        //사무실표현
         for(int i=1;i<=h;i++){
             String row = br.readLine();
             for(int j=1;j<=w;j++){
                 map[i][j] = row.charAt(j-1);//사무실은 1부터, 인풋은 0부터 시작
             }
         }

         String haveKey = br.readLine();
         if(!haveKey.equals("0")){
             //가진 키가 있는 경우

             for(int i=0;i<haveKey.length();i++){
                 key[haveKey.charAt(i)-'a'] = true;
             }
         }

         bw.write(bfs()+"\n");
    }

    static int bfs(){
        int res = 0;//문서 수

        //기본 bfs 재료
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        q.offer(new int[]{0,0});
        visited[0][0] = true;

        while(!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.poll()[1];


            for(int i=0;i<4;i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr<0||nr>=map.length||nc<0||nc>=map[0].length) continue;//범위초과
                if(visited[nr][nc]) continue;//방문초과

                //이제부터 값 사용
                char val = map[nr][nc];

                if(val=='*') continue;//값초과

                if(val>='A' && val<='Z'){
                    //문을 만난 경우

                    if(key[val-'A']) {
                        //열쇠 보유 경우

                        //bfs 일반
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr,nc});
                    }else{
                        //열쇠 미보유 경우

                        //가능성의 영역에 추가
                        oddsList[val-'A'].add(new int[]{nr,nc});
                    }
                }else if(val>='a' && val<='z'){
                    //열쇠 만난 경우

                    if(key[val-'a']){
                        //기존보유열쇠인 경우

                        visited[nr][nc] = true;
                        q.offer(new int[]{nr,nc});
                    }else{
                        //처음 찾은 열쇠인 경우
                        
                        key[val-'a'] = true;//열쇠보유처리

                        //가능성 현실화
                        for(int[] posi:oddsList[val-'a']){
                            int oddsR = posi[0];
                            int oddsC = posi[1];

                            visited[oddsR][oddsC] = true;
                            q.offer(new int[]{oddsR,oddsC});
                        }
                    }

                }else if(val=='$'){
                    res++;
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr,nc});
                }else{
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr,nc});
                }
            }
        }
        return res;
    }
}