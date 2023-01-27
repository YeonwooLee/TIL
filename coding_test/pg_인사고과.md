#### [문제 정보]
>  PriorityQueue / pg_3 / 120++ / X / 인사고과(https://school.programmers.co.kr/learn/courses/30/lessons/152995)

#### [문제 요약]
태도점수+동료점수로 이뤄진 직원 평가 점수에서 [0]번 인덱스에 주어진 직원의 순위를 구한다.

임의의 직원 A, B가 있을 때, 

`(A.태도점수 < B.태도점수) && (A.동로점수<B.동료점수)` 이면 A는 과락
* 과락은 등수에 포함 안함



#### [풀이 과정]
1. Score클래스 생성(태도점수, 동료점수, 주인공인지?, 과락인지?)
2. `PriorityQueue<Score>` 생성
3. rank = 0, idx =0, lastScore
4. pq에서 하나씩 뽑는다
    - 과락인지 확인하고 과락이면 continue(주인공이면 return)
5. pq값이 이전 값과 다르면 
    - lastScore 업데이트
    - rank = idx
    - cur.rank = rank
6. pq값이 이전 값과 같으면
    - cur.rank = rank
7. 주인공이면 등수 리턴

#### [시간 복잡도]
- O(N^2)
> poll() 한 번 마다 n짜리 배열 순회
#### [틀린 이유]
1. O(N^2) 알고리즘 사용하고 싶지않아서 오래 고민함
2. 처음에 과락인사람 등수포함 안되는거 생각 못해서 등수 틀리게 구함
3. 30분 넘었는데 계속 풀었음
#### [느낀점]
1. 수업중에 코테에 집중을 유지하는 것이 쉽지 않다
2. 수업중 코테는 시간에 대한 룰을 다시 정해야 할 것 같기도 함
3. 손으로 먼저 풀고 코딩하기 룰 지키기
#### [해결 코드]
```java
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] scores) {
        //
        PriorityQueue<Score> pq = new PriorityQueue<>();
        
        // N log N
        pq.offer(new Score(0,scores[0]));//진석
        for(int i=1;i<scores.length;i++){//비진석
            pq.offer(new Score(i,scores[i]));
        }
        
        int rank = 0;
        int maxScore = Integer.MAX_VALUE;
        


        int idx = 0;
        while(!pq.isEmpty()){
            Score cur = pq.poll(); //현재애
            //과락체크
            cur.failCheck(scores);
            if(cur.isFail){//과락자
                if(cur.isJiho) return -1;//지호자
                continue;//비지호자
            }
            idx++;
            //비과락자
            if(cur.sum ==maxScore){//동점자
                cur.rank = rank;
            }else{//비동점자
                maxScore=cur.sum;
                rank=idx;
                cur.rank = rank;
            }
            if(cur.isJiho) return cur.rank;
            
        }
        return -1;
    }
 
    static class Score implements Comparable<Score>{
        int at;//태도
        int co;//동료
        int sum;//총점
        int rank;//순위
        boolean isJiho;//지호인지
        boolean isFail;//과락인지
        @Override
        public int compareTo(Score o){
            return o.sum - this.sum;
        }
        public Score (int i,int[] score){
            if(i== 0 ){
                this.isJiho = true;
            }
            this.at = score[0];
            this.co = score[1];
            this.sum = at+co;
        }
        
        public void failCheck(int[][] scores){
            for(int[] score: scores){
                if(score[0]>at){
                    if(score[1]>co){
                        isFail = true;
                        return;
                    }
                }
            }
        }
    }
}
```