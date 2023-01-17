# Quick Sort

https://todaycode.tistory.com/52



- #### time complexity

  - Worst = O(n^2)
  - Best = 
  - **Average = nlogn**

- #### in-place vs not-in-place

  - **in-place**

- #### stable vs unstable

  - **unstable** 
    - [2, 2, 1] 오름차순 정렬시 stable

```java
package sort.quick_sort;
public class MyQuickSort {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        int[] arr = { 3, 1, 7, 4, 5, 1, 8, 2,3,3,3,3,3,3,3 };
        quickSort(arr);
        for (int a : arr) {
            System.out.println(a);
        }

    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        // 더 이상 분할이 되지 않으면 종료
        if (left >= right) return;

        int mid = partition(arr, left, right);
        quickSort(arr, left, mid - 1);
        quickSort(arr, mid, right);
    }

    // 분할
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];

        // 엇갈리지 않으면 계속 반복
        while (left <= right) { // 1. 배열에 값이 중복해서 들어가는 경우는 <=

            // left와 right 찾기
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;

            // 엇갈리지 않으면 swap
            if (left <= right) { // 2. 1과 동일
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
```



