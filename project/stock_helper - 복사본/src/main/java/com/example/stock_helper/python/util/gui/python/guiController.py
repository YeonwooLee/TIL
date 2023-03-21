
import pyautogui
import datetime
import sys
import time
import json

# python -m pip install --upgrade pip
# pip install pyautogui
# pip install pip install pypiwin32
# pip install bs4
# pip install pynput
# pip install mouse
# pip install webdriver_manager
# pip install opencv-python
# pip install selenium
# pip install packaging


#화면에 img가 보이면 {true,imageposition}  리턴
def imgExistWithConfidence(img,confidenceRate):
    result = {'exist': False}
    imgPosition = pyautogui.locateOnScreen(img, confidence=confidenceRate)
    if imgPosition != None:
        result['exist'] = True
        result['imgPosition'] = imgPosition
    return result
# #화면에 img가 보이면 {true,imageposition}  리턴
# def imgExist(img):
#     result = {'exist': False}
#     imgPosition = pyautogui.locateOnScreen(img, confidence=0.95)
#     if imgPosition != None:
#         result['exist'] = True
#         result['imgPosition'] = imgPosition
#     return result


#이미지로 마우스 이동, 클릭 , 이미지 나타날때까지 대기
def mouseToImgAndClick(img,confidenceRate): #confidence = 일치율
    while True:
        imgExistRst = imgExistWithConfidence(img,confidenceRate)
        if imgExistRst['exist']:
            # print("imgPosition is = ", imgExistRst['imgPosition'])
            pyautogui.moveTo(imgExistRst['imgPosition'])
            pyautogui.click()
            break
        # print('searching')


#나타났다사라짐
def disappear(imgFileName,confidence):
    while not imgExistWithConfidence(imgFileName,confidence)['exist']:
        pass
        # print(imgFileName+" 의 등장을 기다리는중")
        # print(imgExistWithConfidence(imgFileName,confidence)['exist'])
    while imgExistWithConfidence(imgFileName,confidence)['exist']:
        pass

if __name__ == "__main__":
    waitMax = int(sys.argv[1:][0])
    # print(waitMax)

    confirm_exit_cp = 'C:\\my2023programs\\TIL\\project\\stock_helper\\src\\main\\java\\com\\example\\stock_helper\\python\\util\\gui\\python\\image\\confirm_exit_cp.png'
    confirm_exit_cp_yes = 'C:\\my2023programs\\TIL\\project\\stock_helper\\src\\main\\java\\com\\example\\stock_helper\\python\\util\\gui\\python\\image\\confirm_exit_cp_yes.png'
    flag = False
    for sec in range(waitMax):
        conFirmExist = imgExistWithConfidence(confirm_exit_cp,0.95)['exist']
        if conFirmExist:
            # print(conFirmExist)
            mouseToImgAndClick(confirm_exit_cp_yes,0.95)
            flag = True
            break
        time.sleep(1)

    mouseToImgAndClick("C:\\my2023programs\\TIL\\project\\stock_helper\\src\\main\\java\\com\\example\\stock_helper\\python\\util\\gui\\python\\image\\min_size_btn.png",0.95)

            
    if flag:
        result = '기존 프로그램 종료'
    else:
        result = '기존 프로그램 없음'

        

    print(json.dumps(result))