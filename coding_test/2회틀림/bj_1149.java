package daily.y_2023.m_03.d_29.bj_1149;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int RED = 0;
    static int GREEN = 1;
    static int BLUE = 2;

    static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    static int N;//집 수
    public static void main(String[] args) throws IOException{
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] price = new int[N][3];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            price[i][RED] = Integer.parseInt(st.nextToken());
            price[i][BLUE] = Integer.parseInt(st.nextToken());
            price[i][GREEN] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<N;i++){
            price[i][RED]+=Math.min(price[i-1][GREEN],price[i-1][BLUE]);
            price[i][GREEN] += Math.min(price[i-1][RED],price[i-1][BLUE]);
            price[i][BLUE] += Math.min(price[i-1][RED],price[i-1][GREEN]);
        }

        System.out.println(Math.min(Math.min(price[N-1][RED],price[N-1][BLUE]),price[N-1][GREEN]));

    }
}
