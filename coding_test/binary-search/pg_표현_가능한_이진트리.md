```java
#### [문제 정보]

>  Binary Search / pg_3 / 120++ / X / 표현 가능한 이진트리 (https://school.programmers.co.kr/learn/courses/30/lessons/150367)

#### [문제 요약]

> 1. 주어진 수를 이진수로 바꾼다.
> 2. 포화이진탐색트리를 중위순회하여 이 숫자를 만들 수 있는지 판단한다.

#### [풀이 과정]

1. 해당 수의 2진법 길이 계산 = floor(log(num)) + 1
2. 해당 수의 포화이진트리 길이 계산  = 2^x -1에서 x에 1씩 증가시키면서 1번의 길이와 같거나 크면 break
3. 포화이진트리를 나타내는 배열에 0b(num)을 넣는다. 
   1. num = 4라면 배열은 [0,1,0]으로 앞에 0을 넣어서 2^x - 1 형식을 맞춰준다.
4. 3에서 구한 배열을 이분탐색한다. 
5. mid가 0이면 포화이진트리를 만들려고 임의로 넣은 더미노드로 자식 노드가 있을 수 없다.
6. 이분탐색의 재귀에서 앞단계 재귀에서 0이었던 부분에 대해 현재 단계에서 num을 만들려면 1을 요구한다면 만들 수 없는 num 으로 판단한다.

#### [시간 복잡도]

#### [틀린 이유]

- 트리에 대한 지식 부족
- 포화이진트리 길이 계산할 생각 하지 못함

#### [느낀점]

- 답을 봐도 이해하기 어려운 문제에 대해 너무 많은 시간이 걸리는 문제에 대한 대책 필요.

#### [해결 코드]

```java
package daily.y_2023.m_01.d_29.pg_표현_가능한_이진트리;

class Solution {
    public boolean[] target; //2진법으로 변환한 배열 (좌측 0 패딩 포함
    public int result;

    //Root 부터 탐색
    public void solve(int s, int e, boolean isEnd) {
        int mid = (s + e) / 2;//루트노드의 인덱스
        boolean cur = target[mid];//루트노드의 값 : 0(false)이면 다음 순회 isEnd가 트루임

        //부모노드가 0으로 cur이 true여선 안됨(정확히는 cur 존재 불가능)
        if (isEnd && cur) {//루트노드가 없는데 자식 노드가 있다.
            result = 0;//안된당
            return;
        }
        //마지막 노드가 아닐 경우, 계속 진행
        if (s != e) {
            solve(s, mid-1, !cur);
            solve(mid+1, e, !cur);
        }
    }

    public int[] solution(long[] numbers) {
        int[] res = new int[numbers.length];
        for (int ind = 0; ind < numbers.length; ind++) {
            result = 1; //일단 된다고 가정
            long num = numbers[ind];
            //2진법 변환한 target 배열 생성
            //해당 수의 2진법 길이 계산
            int len = (int)Math.floor(Math.log(num) / Math.log(2)) + 1;
            //해당 수의 포화 이진트리 길이 계산
            int exp = 1;
            int treeLen = 0;
            while(true) {
                treeLen = (int)Math.pow(2, exp++) - 1;
                if (treeLen >= len) break;
            }

            target = new boolean[treeLen];
            int i = treeLen - 1;
            while(true) {
                long div = num / 2;
                long mod = num % 2;
                num = div;
                target[i--] = (mod == 1);
                if (div == 1) {
                    target[i] = true;
                    break;
                } else if (div == 0)
                    break;
            }
            solve(0, treeLen - 1, false);
            res[ind] = result;
        }
        return res;
    }
}
```xxxxxxxxxx package daily.y_2023.m_01.d_29.pg_표현_가능한_이진트리;class Solution {    public boolean[] target; //2진법으로 변환한 배열 (좌측 0 패딩 포함    public int result;    //Root 부터 탐색    public void solve(int s, int e, boolean isEnd) {        int mid = (s + e) / 2;//루트노드의 인덱스        boolean cur = target[mid];//루트노드의 값 : 0(false)이면 다음 순회 isEnd가 트루임        //부모노드가 0으로 cur이 true여선 안됨(정확히는 cur 존재 불가능)        if (isEnd && cur) {//루트노드가 없는데 자식 노드가 있다.            result = 0;//안된당            return;        }        //마지막 노드가 아닐 경우, 계속 진행        if (s != e) {            solve(s, mid-1, !cur);            solve(mid+1, e, !cur);        }    }    public int[] solution(long[] numbers) {        int[] res = new int[numbers.length];        for (int ind = 0; ind < numbers.length; ind++) {            result = 1; //일단 된다고 가정            long num = numbers[ind];            //2진법 변환한 target 배열 생성            //해당 수의 2진법 길이 계산            int len = (int)Math.floor(Math.log(num) / Math.log(2)) + 1;            //해당 수의 포화 이진트리 길이 계산            int exp = 1;            int treeLen = 0;            while(true) {                treeLen = (int)Math.pow(2, exp++) - 1;                if (treeLen >= len) break;            }            target = new boolean[treeLen];            int i = treeLen - 1;            while(true) {                long div = num / 2;                long mod = num % 2;                num = div;                target[i--] = (mod == 1);                if (div == 1) {                    target[i] = true;                    break;                } else if (div == 0)                    break;            }            solve(0, treeLen - 1, false);            res[ind] = result;        }        return res;    }}java
```