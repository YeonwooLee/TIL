# -*- coding: utf-8 -*-
#자바에 전달되는 전체 종목 핵심 정보의 리스트 반환할 때 사용
import win32com.client
from allStockDict import makeAllStockDict
import sys
import json

allStockDict = makeAllStockDict()

# 연결 여부 체크 - 0 = 연결ㄴㄴ, 1 = 연결 ㅇㅇ / 현재 정상 작동 안함
def check_connect():
    objCpCybos = win32com.client.Dispatch("CpUtil.CpCybos")
    bConnect = objCpCybos.IsConnect
    if (bConnect == 0):
        print("PLUS가 정상적으로 연결되지 않음. ")
    return True

#종목명을 코드로
def nameToCode(name):
    objCpStockCode = win32com.client.Dispatch("CpUtil.CpStockCode")
    code = objCpStockCode.NameToCode(name)
    return code

#코드를 종목명으로
def codeToName(code):
    objCpStockCode = win32com.client.Dispatch("CpUtil.CpStockCode")
    name = objCpStockCode.CodeToName(code)
    return name

#한글키로 되어있는것 영어로 변경
def getDetail(stockCode):
    riseRateRank = allStockDict['riseRates'] #상승순위
    transactionAmountRank = allStockDict['transactionAmount'] #거래대금순위
    stocks = allStockDict['stockInfo']

 
    stockInfo = stocks[stockCode]
    # print(stockInfo) #{'종목코드': 'A005930', '시간': 1600, '현재가': 61800, '거래대금': 595372610000, '종목명': '삼성전자', '전일종가': 61500, '상승률': 0.49}
    
    stockRise = stockInfo['상승률']
    stockTransactionAmount = stockInfo['거래대금']
    stockName = stockInfo['종목명']
    openingPrice = stockInfo['시가']
    currentPrice = stockInfo['현재가']
    

    riseRank = riseRateRank.index(stockRise)#인덱스를 통해 상승률 순위를 정한다
    amountRank = transactionAmountRank.index(stockTransactionAmount)#인덱스를 통해 거래대금 순위를 정한다

    result = {}
    result['stockCode'] = stockCode #종목코드
    result['stockName'] = stockName #종목명
    result['stockRise'] = stockRise #상승률
    result['riseRank'] = riseRank #상승순위
    result['stockTransactionAmount'] = stockTransactionAmount #거래대금
    result['amountRank'] = amountRank #거래대금순위
    result['searchTime'] = str(stockInfo['시간'])[:2]+":"+str(stockInfo['시간'])[2:]
    result['openingPrice'] = openingPrice #시가
    result['currentPrice'] = currentPrice #현재가


    return result

# 모든주식 info가 담긴 리스트 생성
def makeAllStockInfo():
    allStockList = []
    for key in allStockDict['stockInfo']:
        allStockList.append(getDetail(key))
    return allStockList

def main():
    # Do some processing with the arguments

    # Return the result
    
    allStockList = makeAllStockInfo()
    # for i in range(10):
    #     print(allStockList[i])

    return allStockList

if __name__ == "__main__":
    
    # result = main(sys.argv[1:])
    result = main()
    
    ###테스트 공간 차후 삭제 ###
    for i in range(len(result)):
        result[i]['stockName'] = codeToName(result[i]['stockCode'])
    
    ##테스트 공간 끝##


    result = json.dumps(result)
    print(result)


    