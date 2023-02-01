# -*- coding: utf-8 -*-
#자바에 전달되는 전체 종목 핵심 정보의 리스트 반환할 때 사용
import win32com.client
from allStockDict import makeAllStockDict
import sys
import json
from const import rqParam

allStockDict = makeAllStockDict() #기본 주식

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

#kEY한글 -> KEY영어로 변경
#상승률 및 거래대금 순위 책정
def getDetail(stockCode):
    riseRateRank = allStockDict['riseRates'] #상승순위
    transactionAmountRank = allStockDict['transactionAmount'] #거래대금순위
    stocks = allStockDict['stockInfo']

 
    stockInfo = stocks[stockCode]
    # print(stockInfo) #{'종목코드': 'A005930', '시간': 1600, '현재가': 61800, '거래대금': 595372610000, '종목명': '삼성전자', '전일종가': 61500, '상승률': 0.49}
    
 


    riseRank = riseRateRank.index(stockInfo['상승률'])#인덱스를 통해 상승률 순위를 정한다
    amountRank = transactionAmountRank.index(stockInfo['거래대금'])#인덱스를 통해 거래대금 순위를 정한다

    result = {}
    for key in rqParam:
        paramEng = rqParam[key]['paramENG']
        paramKR = rqParam[key]['paramKR']

        result[paramEng] = stockInfo[paramKR]
    # result['stockCode'] = stockCode #종목코드
    # result['stockName'] = stockInfo['종목명']
    result['stockRise'] = stockInfo['상승률']
    result['riseRank'] = riseRank #상승순위
    # result['stockTransactionAmount'] = stockInfo['거래대금']
    result['amountRank'] = amountRank #거래대금순위
    result['searchTime'] = str(stockInfo['시간'])[:2]+":"+str(stockInfo['시간'])[2:]
    # result['openingPrice'] = stockInfo['시가']
    # result['currentPrice'] = stockInfo['현재가']


    return result

# 모든주식 info가 담긴 리스트 생성
def makeAllStockInfo():
    allStockList = []
    for key in allStockDict['stockInfo']:
        allStockList.append(getDetail(key))
    return allStockList


if __name__ == "__main__":
    
    # result = main(sys.argv[1:])
    result = makeAllStockInfo()
    
    #8자제한 해제
    for i in range(len(result)):
        result[i]['stockName'] = codeToName(result[i]['stockCode'])


    result = json.dumps(result)
    print(result)


    