import win32com.client

def check_connect():
    instCpCybos = win32com.client.Dispatch("CpUtil.CpCybos")
    print(instCpCybos.IsConnect)
def main(args):

    # Do some processing with the arguments

    # Return the result
    result = {}
    stock_name = args[0]

    result['stock_name'] = stock_name
    result['stock_code'] = 'temp_code'
    return result

if __name__ == "__main__":
    check_connect()
    quit()
    import sys
    result = main(sys.argv[1:])
    print(result) # 출력을 해야 들어감