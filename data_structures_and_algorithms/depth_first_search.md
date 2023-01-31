## 개념
> 대표적인 그래프 탐색 알고리즘 DFS, BFS 중 하나로, 깊이 우선 탐색 알고리즘이다.


다음과 같은 절차로 그래프를 탐색한다
> 1. 현재 탐색 중인 노드에 다음 level의 노드(자식노드)가 있다면 자식 노드를 먼저 탐색한다. 
> *이 때 자식 노드는 가장 낮은 숫자의 노드가 우선권을 갖는다.
> 2. 더이상 자식 노드가 없는 노드에 도착하면 이전 레벨의 노드로 돌아가 다음 자식노드를 탐색한다.


아래와 같은 순서로 탐색한다
![image](https://user-images.githubusercontent.com/29223461/183286927-5a32bdbb-9c1e-42db-90c7-0274c721419f.png)


## 구현

#### dfs를 이차원 배열에서 구현하면 아래와 같이 구현할 수 있다.
이차원 배열에서 다음 level의 노드란,  '다음 탐색할 수 있는 칸(아직 확인하지 않은)'을 의미한다고 할 수 있다.

dr과 dc를 이용해 탐색 우선순위를 정해준다
그래프로 치면 자식노드가 있는지 확인하는 과정이고 

dr,dc의 인덱스 순서가 상우하좌로 되어있는데, 그래프에서는 자식노드 중 번호가 낮은 노드가 우선순위를 갖는다면
이차원 배열에서는 현재 칸을 기준으로 상우하좌 순서로 우선순위를 갖는다는 의미이다.

```
package test;
public class Test {
    static int[][] arr = new int[6][6];
    //상우하좌
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static boolean[][] visited = new boolean[6][6];
    public static void main(String[] args){
        printArr();
        dfs(0,0,0);
        printArr();
    }

    static void dfs(int num,int r,int c){
        visited[r][c]= true;
        arr[r][c]=num;
        for(int i=0;i<4;i++){
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(nr>=0 && nr<6 && nc>=0 &&nc<6){
                if(!visited[nr][nc]) {
                    dfs(num + 1, nr, nc);
                    continue;//단순 dfs식 그래프 탐색시 여기서 continue를 걸어줌
                }
            }
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
상우하좌 순서로 우선순위를 걸었을 때 그래프 탐색 순서는 아래 배열의 숫자와 같다. 상우하좌의 우선순위로 그래프(배열)을 탐색한 것을 확인할 수 있다.
![image](https://user-images.githubusercontent.com/29223461/183286380-aed00200-88f6-42e8-b212-6d81a70db8ed.png)






---




#### 모든 경우의 수를 따지는 브루트 포스 문제의 경우  dfs를 응용하여 해결할 수 있다.
아래 코드와 같이 다음 level의 탐색 후 원상복구 코드를 삽입하여 모든 경우의 수를 대입해 볼 수 있다.
```
visited[nr][nc]=false;//완전 탐색의 경우 원상복구
```

전체 코드는 다음과 같다
```
package test;


public class Test {
    static int[][] arr = new int[6][6];
    //상우하좌
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static boolean[][] visited = new boolean[6][6];
    public static void main(String[] args){
        printArr();
        dfs(0,0,0);
        printArr();
    }

    static void dfs(int num,int r,int c){
        visited[r][c]= true;
        arr[r][c]=num;
        for(int i=0;i<4;i++){
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(nr>=0 && nr<6 && nc>=0 &&nc<6){
                if(!visited[nr][nc]) {
                    dfs(num + 1, nr, nc);
//                    continue;//단순 dfs식 그래프 탐색시 여기서 continue를 걸어줌
                    visited[nr][nc]=false;//완전 탐색의 경우 원상복구
                }
            }
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
결과는 아래와 같다
![image](https://user-images.githubusercontent.com/29223461/183286568-2dcc81b2-1405-4852-a556-dbaac357793c.png)

dfs를 거친 이차원 배열을 보니 상우하좌의 우선순위가 정반대로 되어있다. 
이것의 의미는 다음과 같다.

출력된 배열은 마지막 탐색 시점의 탐색 순서를 나타낸다.
화면에 출력되진 않았지만 이미 이 앞에서 모든 경우의 수로 그래프 탐색을 마친 것이고

가장 마지막 우선순위를 가진 좌하우상(상우하좌의 반대)의 결과가 배열에 남겨진채로 dfs가 종료된 것이다.



## dfs의 장점, 유리한 문제
#### 장점: dfs의 장점은 해가 깊은 곳에 있을 떄 찾기 유리하다는 것이다. 
#### 이것을 문제에 적용하면 어떻게 해석할 수 있을까?

오늘 풀었던 연산자 끼워넣기(https://www.acmicpc.net/problem/14888)가 dfs가 유리한 대표적인 예라고 할 수 있다.
다음과 같이 숫자들 사이에 연산자를 넣어보고 최댓값, 최솟값을 구하는 문제이다.
![image](https://user-images.githubusercontent.com/29223461/183287186-ae180dfb-c3e8-4e60-849d-9d9b512ff28c.png)

이게 dfs가 유리한 문제인 이유는, 연산자 칸 전부에 연산자를 넣어야하기 때문이다.
가장 마지막 칸(=가장 깊은 노드)까지 연산자를 다 넣어봐야 알 수 있다. 즉, 해가 깊은 곳에 있다.
![image](https://user-images.githubusercontent.com/29223461/183287265-f5619f09-7b6c-4db7-aaa0-4888f4590150.png)


### 이 문제를 BFS가 유리한 문제로 바꿀려면 어떻게 해야할까?
그렇다. 가장 적은 수의 연산자만 사용해서 특정 값 n을 넘길 때, 사용된 연산자의 수를 구하는 문제로 바꾸면 된다.
(이 때, 연산자는 맨 앞 공간부터 채워야함)

![image](https://user-images.githubusercontent.com/29223461/183287421-7e4a95b1-b763-4404-858b-13b3449a991d.png)

n의 값에 따라 다르겠지만 끝까지 다 들어갈 필요가 없을 때, 
즉, 최단거리를 구할 때는 BFS를 쓰면 유리하다.

BFS에 대해서는 다음편에서 자세히 다루도록 한다.