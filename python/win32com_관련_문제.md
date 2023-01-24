### 문제상황

$ python getStockDetail.py test
Traceback (most recent call last):
  File "C:\ProgramData\Anaconda3\lib\site-packages\win32com\client\dynamic.py", line 89, in _GetGoodDispatch
    IDispatch = pythoncom.connect(IDispatch)
**pywintypes.com_error: (-2147221021, '작업을 사용할 수 없습니다.', None, None)**

During handling of the above exception, another exception occurred:

Traceback (most recent call last):
  File "getStockDetail.py", line 22, in <module>
    result = main(sys.argv[1:])
  File "getStockDetail.py", line 7, in main
    check_connect()
  File "getStockDetail.py", line 4, in check_connect
    instCpCybos = win32com.client.Dispatch("CpUtil.CpCybos")
  File "C:\ProgramData\Anaconda3\lib\site-packages\win32com\client\__init__.py", line 95, in Dispatch
    dispatch, userName = dynamic._GetGoodDispatchAndUserName(dispatch,userName,clsctx)
  File "C:\ProgramData\Anaconda3\lib\site-packages\win32com\client\dynamic.py", line 114, in _GetGoodDispatchAndUserName
    return (_GetGoodDispatch(IDispatch, clsctx), userName)
  File "C:\ProgramData\Anaconda3\lib\site-packages\win32com\client\dynamic.py", line 91, in _GetGoodDispatch
    IDispatch = pythoncom.CoCreateInstance(IDispatch, None, clsctx, pythoncom.IID_IDispatch)
**pywintypes.com_error: (-2147221164, '클래스가 등록되지 않았습니다.', None, None)**



### 원인

python 64bit 사용



### 해결

1. python 32bit 설치
2. 새로 깐 32bit 버전으로 환경변수 변경하여 사용



---

