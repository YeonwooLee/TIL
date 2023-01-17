# Selection Sort

- #### time complexity

  - **Worst = O(n^2)** 
  - Best = O(n^2)
  - Average O(n^2)

- #### in-place vs not-in-place

  - **in-place**

- #### stable vs unstable

  - **unstable**
    - [2, 2, 1] 오름차순 정렬시 stable 깨짐

```java
public class MySelectionSort {
    public static void sort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            int maxIdx=i;
            for(int j=i;j<arr.length;j++){
                if(arr[maxIdx]<arr[j]){
                    maxIdx=j;
                }
            }
            //swap
            swap(arr,i,maxIdx);
        }
    }
    public static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
```