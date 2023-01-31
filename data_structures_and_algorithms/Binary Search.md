# Binary Search

- 이진 탐색: 정렬되어 있는 리스트에서 탐색 범위를 절반씩 좁혀가며 데이터를 탐색하는 방법
  \- 이진 탐색은 시작점, 끝점, 중간점을 이용해 탐색범위를 설정

- 시간 복잡도
  - 단계마다 탐색 범위를 2로 나누는 것과 동일하므로 연산 횟수는 log2N에 비례
  - 이진탐색은 탐색 범위를 절반씩 줄이며, 시간 복잡도는 O(logN)을 보장



```javascript
재귀적 구현
function binarySearch(array, target, start, end) {
  	mid = (start + end) / 2
	if (start > end) {
    	return Null
    }
  	else if (array[mid] == target) {
    	return mid         
    }
    else if (array[mid] > target) {
    	return binarySearch(array, target, start, mid -1)
    }
  return binary_search (array, target, mid+ 1, end)
}
```

```javascript
반복문 구현
function binarySearch(array, target, start, end) {
	while (start <= end) {
    	mid = (start + end) / 2
     if (array[mid] === target) {
     	return mid
     }
     else if (array[mid] > target {
             end = mid - 1 // 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인 
      }
     start = mid + 1 //중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
    }
  return None
}
```