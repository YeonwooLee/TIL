package bj_12865;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int K;

    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        int[] weights = new int[N+1];
        int[] values = new int[N+1];

        int[][] dp = new int[N+1][K+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=N;i++){
            int curW = weights[i];
            int curV = values[i];
            for(int k=1;k<=K;k++){
                if(curW>k){
                    dp[i][k] = dp[i-1][k];
                }else{
//                    dp[i][k] = ;
//                    dp[i][k] = dp[i-1][k];
                    dp[i][k] = Math.max((dp[i-1][k-curW]+curV), dp[i-1][k]);
                }
            }
        }
        System.out.println(dp[N][K]);

    }
}
