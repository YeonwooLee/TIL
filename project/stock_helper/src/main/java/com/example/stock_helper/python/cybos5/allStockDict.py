import sys

import win32com.client
import ctypes
 
################################################
# PLUS 공통 OBJECT
g_objCodeMgr = win32com.client.Dispatch('CpUtil.CpCodeMgr')
g_objCpStatus = win32com.client.Dispatch('CpUtil.CpCybos')
g_objCpTrade = win32com.client.Dispatch('CpTrade.CpTdUtil')
 
 
################################################
# PLUS 실행 기본 체크 함수
def InitPlusCheck():
    # 프로세스가 관리자 권한으로 실행 여부
    if ctypes.windll.shell32.IsUserAnAdmin():
        print('정상: 관리자권한으로 실행된 프로세스입니다.')
    else:
        print('오류: 일반권한으로 실행됨. 관리자 권한으로 실행해 주세요')
        return False
 
    # 연결 여부 체크
    if (g_objCpStatus.IsConnect == 0):
        print("PLUS가 정상적으로 연결되지 않음. ")
        return False
 
    # # 주문 관련 초기화 - 계좌 관련 코드가 있을 때만 사용
    # if (g_objCpTrade.TradeInit(0) != 0):
    #     print("주문 초기화 실패")
    #     return False
 
    return True
 
 # 수익률 소수점 2자리까지 표기
def calRiseRate(today_price, yesterday_price):
    return round(today_price*100/yesterday_price-100,2)
    
class CpMarketEye:
    def __init__(self):
        self.objRq = win32com.client.Dispatch("CpSysDib.MarketEye") #마켓아이 객체 생성
        self.RpFiledIndex = 0
 
 
    def Request(self, codes, dataInfo):
        rqField = [0,1,4,11,17,23]  # 요청 필드
        rqParam = {
            0:'종목코드',
            1:'시간',
            4:'현재가',
            11:'거래대금',
            17:'종목명',
            23:'전일종가'
        }
 
        self.objRq.SetInputValue(0, rqField)  # 요청 필드
        self.objRq.SetInputValue(1, codes)  # 종목코드 or 종목코드 리스트
        self.objRq.BlockRequest()
 
        # 현재가 통신 및 통신 에러 처리
        rqStatus = self.objRq.GetDibStatus()
        # print("통신상태", rqStatus, self.objRq.GetDibMsg1())
        if rqStatus != 0:
            return False
 
        cnt = self.objRq.GetHeaderValue(2) #종목개수
        # print(cnt,'cnt')
 
        for i in range(cnt):
            codeIdx = list(filter(lambda x: rqParam[x]=='종목코드',rqParam.keys()))[0] #밸류가 종목코드인 key 가져옴
            
            stockCode = self.objRq.GetDataValue(codeIdx, i)  # 코드
            
            dataInfo['stockInfo'][stockCode]={} #결과집합[현재종목코드]:{현재종목디테일}

            fieldCnt = self.objRq.GetHeaderValue(0)#요청 필드 수
            tdict = {} # temp dict 요청필드인덱스:값
            
            for j in range(fieldCnt):# 요청필드 인덱스
                cur_data = self.objRq.GetDataValue(j,i)#i코드의 j번째필드값

                fieldNumber = rqField[j] #j번째 필드값의 요청num
                fieldName = rqParam[fieldNumber] #요청num의 의미

                if fieldName == '거래대금':
                    dataInfo['transactionAmount'].append(cur_data)

                tdict[fieldName]=cur_data

            riseRate = calRiseRate(tdict['현재가'],tdict['전일종가']) #상승률
            tdict['상승률']=riseRate
            dataInfo['riseRates'].append(riseRate) #전체 상승률 리스트에 삽입

            dataInfo['stockInfo'][stockCode]= tdict #데이터정보에 투입
        return True
 
class CMarketTotal():
    def __init__(self):
        self.dataInfo = {
            'riseRates':[],
            'transactionAmount':[],
            'stockInfo':{}
            }
 
 
    def getAllMarketTotal(self):
        #전체 종목 리스트 생성
        codeList = g_objCodeMgr.GetStockListByMarket(1)  # 거래소
        codeList2 = g_objCodeMgr.GetStockListByMarket(2)  # 코스닥
        
        allcodelist = codeList + codeList2 #전체 종목
        # print('전 종목 코드 %d, 거래소 %d, 코스닥 %d' % (len(allcodelist), len(codeList), len(codeList2)))
 
        #종목 검색용 객체 생성
        objMarket = CpMarketEye()
        rqCodeList = []
        for i, code in enumerate(allcodelist): #전체 종목 리스트 순회
            rqCodeList.append(code) #요청할 리스트에 종목 코드 추가
            if len(rqCodeList) == 200: #200개 한도 차면
                objMarket.Request(rqCodeList, self.dataInfo) #종목정보탐색
                rqCodeList = [] #리스트 비움(새로 200개 채우도록)
                # continue # example에 있었는데 불필요해보여서 주석처리
        # end of for
 
        if len(rqCodeList) > 0: #요청안한 잔여 (1~199개) 종목 검색
            objMarket.Request(rqCodeList, self.dataInfo)
        
        self.dataInfo['riseRates'].sort(reverse=True)
        self.dataInfo['transactionAmount'].sort(reverse=True)
 
    def getMarketTotal(self):
 
        # 시가총액 순으로 소팅
        # data2 = sorted(self.dataInfo.items(), key=lambda x: x[1][1], reverse=True)
        allStockDict = self.dataInfo
        # idx =0
        # for key in allStockDict.keys():
        #     print(key,">>",allStockDict[key]) if idx<10 else None
        #     idx+=1

        return allStockDict
        

def makeAllStockDict():
    objMarketTotal = CMarketTotal()
    objMarketTotal.getAllMarketTotal()
    return objMarketTotal.getMarketTotal()    
 
 
if __name__ == "__main__":
    # objMarketTotal = CMarketTotal()
    # objMarketTotal.getAllMarketTotal()
    # k = objMarketTotal.getMarketTotal()
    # print(k)
                
    res = makeAllStockDict()
    
    print(res['riseRates'][:10])
    print(res['transactionAmount'][:10])
 
    stocks = res['stockInfo']
    for key in stocks:
        val = stocks[key]
        if val['상승률'] >28:
            print('상승상위',val)
        if val['거래대금'] > 320912209000:
            print('대금상위',val)
