# Merge Sort

- #### time complexity

  - Worst = O(nlogn)
  - Best = O(nlogn)
  - Average = nlogn

- #### in-place vs not-in-place

  - **not-in-place**

- #### stable vs unstable

  - **stable** 
    - [2, 2, 1] 오름차순 정렬시 stable

```java
package sort.merge_sort.bj_2751;

import java.io.*;
import java.util.*;

public class MyMerge {
    public static int[] tmp;

    public static void main(String[] args) throws IOException {
        int[] A = {1, 5, 4, 3, 8, 1};
        tmp = new int[A.length];

        merget_sort(A, 1, A.length - 1); // 병합정렬 수행하기
        for (int i = 0; i < A.length; i++) {
            System.out.printf("%3d", A[i]);
        }
    }

    private static void merget_sort(int[] A, int s, int e) {
        if (e - s < 1)
            return;
        int m = s + (e - s) / 2;
        // 재귀함수 형태로 구현
        merget_sort(A, s, m);
        merget_sort(A, m + 1, e);
        for (int i = s; i <= e; i++) {
            tmp[i] = A[i];
        }
        int k = s;
        int index1 = s;
        int index2 = m + 1;
        while (index1 <= m && index2 <= e) { // 두 그룹을 Merge 해주는 로직
            if (tmp[index1] > tmp[index2]) {
                A[k] = tmp[index2];
                k++;
                index2++;
            } else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }
        }
        // 한쪽 그룹이 모두 선택된 후 남아있는 값 정리하기
        while (index1 <= m) {
            A[k] = tmp[index1];
            k++;
            index1++;
        }
        while (index2 <= e) {
            A[k] = tmp[index2];
            k++;
            index2++;
        }

    }
}
```



