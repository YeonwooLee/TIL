# -*- coding: utf-8 -*-
#자바에 전달되는 전체 종목 핵심 정보의 리스트 반환할 때 사용
import win32com.client
from allStockDict import makeAllStockDict, g_objCodeMgr
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
    
    stocks = allStockDict['stockInfo'] 
    stockInfo = stocks[stockCode]

    result = {}
    #기본필드
    for key in rqParam:
        paramEng = rqParam[key]['paramENG']
        paramKR = rqParam[key]['paramKR']

        result[paramEng] = stockInfo[paramKR]

    #조정필요한 필드
    result['stockRise'] = stockInfo['상승률']
    result['searchTime'] = str(stockInfo['시간'])[:2]+":"+str(stockInfo['시간'])[2:]


    #시총구하기
    issuedShare =  result['issuedShare'] #상장주식수
    currentPrice = result['currentPrice'] #현재가격
    maketAmt = issuedShare * currentPrice #시가총액
    if g_objCodeMgr.IsBigListingStock(stockCode) : #CpUtil.CpCodeMgr 서비스 IsBigListingStock(code) : 상장 주식수 20억 이상 여부 리턴
        maketAmt *= 1000
    result['marketCapitalization'] = maketAmt #시총


    #TODO 순위있는 필드 통합 관리
    riseRank =  allStockDict['riseRates'].index(stockInfo['상승률'])#인덱스를 통해 상승률 순위를 정한다
    result['riseRank'] = riseRank #상승순위

    amountRank = allStockDict['transactionAmountRates'].index(stockInfo['거래대금'])#인덱스를 통해 거래대금 순위를 정한다
    result['amountRank'] = amountRank #거래대금순위

    per = stockInfo['per']
    perRank = allStockDict['perRates'].index(per)
    #TODO 순위있는 필드 통합 관리




    result['per'] = round(per,2)
    result['perRank'] = perRank



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


    # #테스트
    # for i in result:
    #     print(i)
    # quit()
    # #테스트끝
    result = json.dumps(result)
    print(result)


    