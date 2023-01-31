# Radix Sort

- #### time complexity

  > k는 자릿수

  - Worst = O(kn)
  - Best = O(kn)
  - Average = O(kn)

- #### in-place vs not-in-place

  - **not-in-place**
    - 자릿수가n인 숫자 저장 공간 생성

- #### stable vs unstable

  - **stable** 
    - [2, 2, 1] 오름차순 정렬시 stable
    - queue 선입 선출
  
- 코드(순한맛)

```java
package sort.radix_sort;

import java.util.PriorityQueue;
import java.util.LinkedList;
import java.io.IOException;
import java.util.Queue;


public class My_Radix_sort {
    public static int[] A;

    public static void main(String[] args) throws IOException {
        A = new int[] {13,24,5346,74};

        //정렬
        Radix_Sort(A, 5);

        //출력
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]+" ");
        }
    }

    public static void Radix_Sort(int[] A, int max_size) {
        int[] output = new int[A.length];
        int jarisu = 1;
        int count = 0;
        Queue<Integer>[] buckets = new LinkedList[10];
        for(int i=0;i<10;i++){
            buckets[i]=new LinkedList<>();
        }

        while (count != max_size) // 최대 자리수 만큼 반복
        {

            for(int i=0;i<A.length;i++){
                int jariNowWatch = A[i]/jarisu; //지금 보고있는 자릿수를 1의자리에 놓은 수
                int nowNum = jariNowWatch%10;//A[i]의 지금 보고있는 자릿수에 있는 수
                buckets[nowNum].offer(A[i]);
            }

            int idx = 0;
            for(int i=0;i<10;i++){
                while(!buckets[i].isEmpty()){
                    int now = buckets[i].poll();
                    output[idx++]=now;
                }
            }


            for (int i = 0; i < A.length; i++) {
                A[i] = output[i]; // 다음 자리 수 이동을 위해 현재 자리수 기준 정렬 데이터 저장
            }
            jarisu = jarisu * 10; // 자리수 증가
            count++;
        }
    }
}
```



- 코드(매운맛)

```java
package sort.radix_sort.bj_10989;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class Main {
    public static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //배열 구성
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        //정렬
        Radix_Sort(A, 5);

        //출력
        for (int i = 0; i < N; i++) {
            bw.write(A[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void Radix_Sort(int[] A, int max_size) {
        int[] output = new int[A.length];
        int jarisu = 1;
        int count = 0;
        while (count != max_size) // 최대 자리수 만큼 반복
        {
            int[] bucket = new int[10];
            for (int i = 0; i < A.length; i++) {
                bucket[(A[i] / jarisu) % 10]++; // 일의 자리 부터 시작
            }
            for (int i = 1; i < 10; i++) { // 합배열을 이용하여 index 계산
                bucket[i] += bucket[i - 1];
            }
            for (int i = A.length - 1; i >= 0; i--) { // 현재 자리수 기준으로 정렬하기
                output[bucket[(A[i] / jarisu % 10)] - 1] = A[i];
                bucket[(A[i] / jarisu) % 10]--;
            }
            for (int i = 0; i < A.length; i++) {
                A[i] = output[i]; // 다음 자리 수 이동을 위해 현재 자리수 기준 정렬 데이터 저장
            }
            jarisu = jarisu * 10; // 자리수 증가
            count++;
        }
    }
}


```



