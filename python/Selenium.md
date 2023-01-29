# **NoSuchElementException**: 

## Element 있는데 안찾아질 때

1. 찾는 element가 iframe 내부에 존재하는 경우

   ```python
   time.sleep(1) #이전 명령 수행 후 안정성 보장
   driver.switch_to.frame(iframe의 id})
   time.sleep(1) #iframe 이동 후 안정성 보장
   ```

   > cf. 상위 프레임으로 이동: driver.switch_to.parent_frame()