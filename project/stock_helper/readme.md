# Stock Helper

# 구현 계획중인 기능 목록
- 석인이 말한 차트특징? 뭔 신호? 그런거 만족했나 알려주기
- 성능개선 / STOCK_SEARCH / !!{주식이름} 검색 속도 개선
  - 일정시간마다 전체 주식 리스트 작성하여 갖고있기
- (기능B)- 거를 종목 키워드
  - 이거 db랑 연동해서 사용
  - 유저별 거를 리스트 생길거 고려 (유저에 외래키로 거를리스트 idx 넣음 될듯?)
    - 유저 개발할 때 개발하면 되니까 이거 만들 때는 안 해도 될 것 같다는 뜻
- DB연동
- Bean에 대해 이해하여 Bean화 할 수 있는 것들 Bean화 하기

# 구현이 완료된 기능 목록

- [기능B]StockFinder.getStocks() 구현 -- 주식 전체 종목 정보 리스트 만드는 기능
  - (기능B)에 거를 종목 키워드 추가(2023.01.26)
    - trash=[인버스,블룸버그,'ETN','선물','HANARO','KBSTAR','TIGER','KOSEF','KINDEX','ARIRANG','KODEX','SMART']

- [기능C]상승률 n% 거래대금 m억 이상인 종목 찾기
  - (기능B) 활용

* [기능A]주식정보검색: input: 주식이름 —> output: 전일대비, 거래대금
  * (기능A)에 {오늘의 상승률 순위} 추가
  * (기능A)에 {오늘의 거래대금 순위} 추가
  * (기능A)에 없는 종목 검색시 예외처리
  * ~~ (기능A)에 여러종목이 검색되는 이름 입력시 맨 처음 등장하는 주식에 대한 정보 리턴 처리~~ 
    * 웹크롤링에서 api사용으로 바꾸면서 현재 사용하지 않음




# 디버깅 해야하는 목록
  - 일정 기간이 지나면 cybos plus5가 꺼짐 (2023-01-25)
    - cybos plus가 꺼지면 있는 주식도 잘못된 주식명이라고 나옴
      - 잘못된 주식명 대신 cybos가 꺼져있다고 출력
    - cybos가 꺼지면 자동으로 재시작
# 해결된 디버깅{원인}{해결방법}

* 종목명에 공백이 있으면 검색되지 않는 문제

  * 해결방법과 원인

    #### #원인

    ```PYTHON
    def main(args):
        # Do some processing with the arguments
    
        # Return the result
        #터미널에서 python 프로그램 실행할 때 ~~.py arg1 arg2 이런식인데
        stock_name = args[0] #이 부분 때문에 공백있는 종목은 공백 앞부분만 들어감
    
        result = getDetail(stock_name)
        return result
    
    if __name__ == "__main__":
        result = main(sys.argv[1:])
        result = json.dumps(result)
        print(result)
    
    ```

    #해결방법

    ```python
    def main(args):
        # Do some processing with the arguments
    
        # Return the result
        
        stock_name = " ".join(args) #args를 " ".join을 이용해 원래 종목명으로 변환
        # print(stock_name,'test stock')
        result = getDetail(stock_name)
        return result
    
    if __name__ == "__main__":
        result = main(sys.argv[1:])
        result = json.dumps(result)
        print(result)
    
    ```

    

* 주식정보검색에서 /start를 매번 입력해야 하는 문제
  {텔레그램 방에서 봇한테 메세지 권한을 줘야함}

- 파이썬 출력 읽을 때 파일 깨짐
