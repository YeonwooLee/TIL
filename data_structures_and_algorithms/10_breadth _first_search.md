## 개념
> 대표적인 그래프 탐색 알고리즘 DFS, BFS 중 하나로,  너비 우선 탐색 알고리즘이다.


다음과 같은 절차로 그래프를 탐색한다
> 1. 현재 탐색 중인 노드와 같은 level의 노드를 우선 탐색한다
> 2. 탐색한 노드를 큐에 담습니다.
> 3. 더이상 동일 level 노드가 없으면 큐에서 순차적으로 다음 level의 노드를 탐색한다


아래와 같은 순서로 탐색한다
![image](https://user-images.githubusercontent.com/29223461/183298267-8daba9e8-c52d-48b9-9f1c-72ba2bc4d934.png)


## 구현

####bfs를 이차원 배열에서 구현하면 아래와 같이 구현할 수 있다.


```
package test;
import java.util.Queue;
import java.util.LinkedList;


public class Test {
    static int[][] arr = new int[6][6];
    //상우하좌
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static boolean[][] visited = new boolean[6][6];
    public static void main(String[] args){
        printArr();
        bfs(0,0);
        printArr();
    }

    static void bfs(int r,int c){
        int num = 0; //배열에 채울 숫자
        Queue<int[]> q = new LinkedList<>();

        //시작점 세팅
        q.offer(new int[] {r,c});
        visited[r][c] = true;//방문처리

        int level = 0;
        while(!q.isEmpty()){
            level++;
            int size = q.size();
            System.out.printf("현재 level은 %d 입니다\n확인할 노드 >>>",level);
            //level, size, 내부 while문을 통해 level 단위로 확인할 수 있다
            while(size-->0){
                int[] now = q.poll();//이번에 확인할 노드
                int row = now[0];
                int col = now[1];
                System.out.printf("(%d, %d) ",row,col);
                arr[row][col] = num;
                num++;
                for(int i=0;i<dr.length;i++){
                    int nr = row+dr[i]; //new row
                    int nc = col+dc[i]; //new column

                    //배열범위, 방문기록 없어야
                    if(nr>=0 && nr<arr.length && nc>=0 && nc<arr.length && !visited[nr][nc]){
                        q.offer(new int[] {nr,nc});//큐에 삽입
                        visited[nr][nc] = true;//방문처리
                    }
                }
            }
            System.out.printf("level %d 종료\n\n",level);

        }


    }
    static void printArr(){
        System.out.println();
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.printf("%3d",arr[i][j]);
            }
            System.out.println();
        }
    }
}

```

#### 결과는 아래와 같다

![image](https://user-images.githubusercontent.com/29223461/183299198-b045ab15-8cc3-4e9f-85a5-e26db0a51e64.png)
![image](https://user-images.githubusercontent.com/29223461/183299205-4b75a4ca-3fdb-43c8-b085-ebdd75f00cdf.png)


# bfs의장점
BFS같은 경우 최단거리를 확인하는데 용이하다
큐에 담기는 노드의 순서가 노드 레벨순서로 들어가기 때문이다.
```
[시작노드, 시작노드+1, 시작노드+1, 시작노드+1+1, 시작노드 +1+1 ... ]
```

#### 최단거리를 찾는 문제에 용이하다
스타트 택시(https://www.acmicpc.net/problem/19238) 문제를 보자
![image](https://user-images.githubusercontent.com/29223461/183299370-ec32b9d0-c7a0-4734-aac7-a537d310b3c2.png)

그림과 같이 

>택시 (출발노드)- 승객(도착노드), 
>승객(출발노드) - 목적지(도착노드)의 

최단거리만 구하면 된다. 그 뒤의 경우의 수는 알 바 아니다.
이런 경우에 BFS가 DFS보다 유리하다.


### 이 문제를 DFS가 유리한 문제로 바꾸려면?
> 잘 모르겠다

> 특정 승객에게 도달하는 최소 이동 횟수와 최대 이동 횟수를 구하는 문제로 바꾸면 될 것 같긴 한데
> dfs로 최단거리를 구하려다 실패했어서 확신을 못하겠다