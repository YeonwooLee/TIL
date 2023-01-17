# Insertion Sort

- #### time complexity

  - **Worst = O(n^2);
  - Best = O(n)
  - Average = 

- #### in-place vs not-in-place

  - **in-place**

- #### stable vs unstable

  - **stable** 
    - [2, 2, 1] 오름차순 정렬시 stable

```c
# include <stdio.h>
# include <stdlib.h>
# define MAX_SIZE 5

void insertion_sort(int list[], int n) {
	for (int i = 1; i < n; i++) {
		int key = list[i];
		int j;
		for (j = i - 1; j >= 0; j--) {
			if (list[j] < key) break;
			list[j + 1] = list[j];
		}
		list[j + 1] = key;
	}
}

void main() {
	int n = MAX_SIZE;//배열 크기
	int* list = (int*)malloc(sizeof(int) * n);//메모리 동적할당
	
	*(list + 0 * sizeof(int)) = 8;
	//list[0] = 8;
	list[1] = 5;
	list[2] = 6;
	list[3] = 2;
	list[4] = 4;
	
	insertion_sort(list, n);
	
	for (int i = 0; i < n; i++) {
		printf("%d", list[i]);
	}

	free(list);
}
```



```java
package sort.insertion_sort;

public class My_insertion_sort {
    static void insertion_sort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int key = arr[i];
            int j;
            for(j=i-1;j>=0;j--){
                if(arr[j]<=key) break;
                arr[j+1]=arr[j];
            }
            arr[j+1] = key;
        }
    }

    public static void main(String[] args){
        int[] arr = {3,2,5,8,1};
        insertion_sort(arr);

        
        for(int i=0;i<arr.length;i++){
            System.out.printf("%2d",arr[i]);
        }
    }
}
		
```

