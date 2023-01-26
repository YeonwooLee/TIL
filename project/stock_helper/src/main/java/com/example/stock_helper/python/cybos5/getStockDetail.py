# -*- coding: utf-8 -*-
#단일 종목에 대해 검색할 때 사용
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

def getDetail(stockName):
    riseRateRank = allStockDict['riseRates']
    transactionAmountRank = allStockDict['transactionAmount']
    stocks = allStockDict['stockInfo']

    stockName = stockName
    stockCode = nameToCode(stockName)

    stockInfo = stocks[stockCode]
    # print(stockInfo) #{'종목코드': 'A005930', '시간': 1600, '현재가': 61800, '거래대금': 595372610000, '종목명': '삼성전자', '전일종가': 61500, '상승률': 0.49}
    
    stockRise = stockInfo['상승률']
    stockTransactionAmount = stockInfo['거래대금']
    
    riseRank = riseRateRank.index(stockRise)
    amountRank = transactionAmountRank.index(stockTransactionAmount)

    result = {}
    result['stockCode'] = stockCode #종목코드
    result['stockName'] = stockName #종목명
    result['stockRise'] = stockRise #상승률
    result['riseRank'] = riseRank #상승순위
    result['stockTransactionAmount'] = stockTransactionAmount #거래대금
    result['amountRank'] = amountRank #거래대금순위

    return result
def main(args):
    # Do some processing with the arguments

    # Return the result
    
    stock_name = " ".join(args)
    # print(stock_name,'test stock')
    result = getDetail(stock_name)
    return result

if __name__ == "__main__":
    result = main(sys.argv[1:])
    result = json.dumps(result)
    print(result)


