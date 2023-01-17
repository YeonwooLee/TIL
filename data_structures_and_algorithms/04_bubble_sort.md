# Bubble Sort

- #### time complexity

  - **Worst = O(n^2)** 
  - Best = Omega(n)
  - Average = 

- #### in-place vs not-in-place

  - **in-place**

- #### stable vs unstable

  - **stable** 
    - [2, 2, 1] 오름차순 정렬시 stable

```java
public class MyBubbleSort {
    static void sort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }
    public static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
```
