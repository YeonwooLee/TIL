# Tree

### 개념

---

- 트리는 계층적 관계(Hierarchical Relationship)를 표현하는 자료구조입니다.
- 자료구조는 근본적으로 무엇인가를 '표현'하는 도구이고, 표현을 위해 저장과 삭제 기능이 제공되는 것으로 이해하는게 옳습니다.  이에 트리는 데이터의 저장, 삭제가 용이하게 정의되어 있는지에 중점을 두기보다는 트리 구조로 이루어진 무엇인가를 표현하기에 적절하게 정의되어있는지 중점을 두고 바라보는 것이 중요합니다.
- 트리의 예시로는 컴퓨터의 디렉토리 구조, 족보, 조직도 등이 있습니다.

- 관련용어![image-20221122071645602](C:\Users\yeonw\AppData\Roaming\Typora\typora-user-images\image-20221122071645602.png)

  - 노드(node):  트리의 구성요소에 해당하는 A, B, C .. 과 같은 요소

  - 간선(Edge): 노드와 노드를 연결하는 연결선

  - 루트 노드(root node): 트리 구조에서 최상위에 존재하는 A와 같은 노드

  - 단말 노드(terminal node): 아래로 또 다른 노드가 연결되어 있지 않은 E, K, L, G ...과 같은 노드

    - 리프 노드라고도 한다

  - 형제 노드: 같은 부모 노드의 자식 노드들

    - B,C,D는 형제 노드

  - 조상 노드: 간선을 따라 루트 노드까지 경로에 있는 모든 노드들

    - K의 조상 노드: F, B, A

  - 서브 트리: 부모 노드와 연결된 간선을 끊었을 때 생성되는 트리

    - 각 노드는 자식 노드의 개수 만큼 서브 트리를 가짐

    - D를 부모로 가지는 H,I,J는 D라는 서브 트리

  - 자손 노드: 서브 트리에 있는 하위 레벨의 노드들

    - B의 자손 노드: E,F,K,L

### 특징

---

- 가지를 늘려가며 뻗어나간다.

### 구현

---



---

채워나갈 리스트

## 트리

- ### 트리 - 배경 지식

  -  [Series: Trees (video)](https://www.coursera.org/learn/data-structures/lecture/95qda/trees)

  - 트리 기초 형태 만들기

  - 순회

  - 알고리즘 다루기

  -  

    BFS(너비-우선 탐색;breadth-first search) and DFS(깊이-우선 탐색;depth-first search)

    - BFS 노트:
      - level order (BFS, 큐를 사용하여)
      - 시간 복잡도: O(n)
      - 공간 복잡도: 최고: O(1) 최악: O(n/2)=O(n)
    - DFS 노트:
      - 시간 복잡도: O(n)
      - 공간 복잡도: 최고: O(log n) - 평균적으로, 트리의 높이이다. 최악: O(n)
      - 중위(inorder) (DFS: 왼쪽, 자신, 오른쪽)
      - 후위(postorder) (DFS: 왼쪽, 오른쪽, 자신)
      - 전위(preorder) (DFS: 자신, 왼쪽, 오른쪽)

- ### 이진 탐색 트리 (BST)

  -  [Binary Search Tree Review (video)](https://www.youtube.com/watch?v=x6At0nzX92o&index=1&list=PLA5Lqm4uh9Bbq-E0ZnqTIa8LRaL77ica6)

  -  [Introduction (video)](https://www.coursera.org/learn/data-structures/lecture/E7cXP/introduction)

  -  [MIT (video)](https://www.youtube.com/watch?v=9Jry5-82I68)

  - C/C++:

    -  [이진 탐색 트리 - C/C++로 구현하기 (영상)](https://www.youtube.com/watch?v=COZK7NATh4k&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&index=28)
    -  [BST 구현 - 스택과 힙에 메모리 할당 (영상)](https://www.youtube.com/watch?v=hWokyBoo0aI&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&index=29)
    -  [이진 탐색 트리에서 가장 작은 원소와 가장 큰 원소 찾기 (영상)](https://www.youtube.com/watch?v=Ut90klNN264&index=30&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P)
    -  [이진 트리의 높이 구하기 (영상)](https://www.youtube.com/watch?v=_pnqMz5nrRs&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&index=31)
    -  [이진 트리 순회 - 너비-우선과 깊이-우선 전략 (영상)](https://www.youtube.com/watch?v=9RHO6jU--GU&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&index=32)
    -  [이진 트리: Level Order Traversal (video)](https://www.youtube.com/watch?v=86g8jAQug04&index=33&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P)
    -  [이진 트리 순회: 전위, 중위, 후위 (영상)](https://www.youtube.com/watch?v=gm8DUJJhmY4&index=34&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P)
    -  [이진 트리가 이진 탐색 트리인지 아닌 지 확인하기 (영상)](https://www.youtube.com/watch?v=yEwSGhSsT0U&index=35&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P)
    -  [이진 탐색 트리에서 노드 삭제하기 (영상)](https://www.youtube.com/watch?v=gcULXE7ViZw&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&index=36)
    -  [Inorder Successor in a binary search tree (video)](https://www.youtube.com/watch?v=5cPbNCrdotA&index=37&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P)

  -  

    구현:

    -  insert // 트리에 어떤 값을 삽입
    -  get_node_count // 저장된 값들의 개수 세기
    -  print_values // 트리 안의 값들을 최소부터 최대까지 출력
    -  delete_tree
    -  is_in_tree // 주어진 값이 트리 안에 있는 지를 반환
    -  get_height // 어떤 노드의 높이를 반환 (노드 하나의 높이는 1이다.)
    -  get_min // 트리에 저장된 값 중 가장 작은 값을 반환
    -  get_max // 트리에 저장된 값 중 가장 큰 값을 반환
    -  is_binary_search_tree
    -  delete_value
    -  get_successor // 값이 주어지면, 다음으로 가장 큰 값을, 없으면 -1을 반환

- ### 힙 / 우선순위 큐 / 이진 힙

  - 트리처럼 보여지지만, 보통은 선형으로 저장됩니다. (배열, 링크드리스트처럼)

  -  [힙(Heap)](https://en.wikipedia.org/wiki/Heap_(data_structure))

  -  [소개 (영상)](https://www.coursera.org/learn/data-structures/lecture/2OpTs/introduction)

  -  [Naive한 구현들 (영상)](https://www.coursera.org/learn/data-structures/lecture/z3l9N/naive-implementations)

  -  [이진 트리 (영상)](https://www.coursera.org/learn/data-structures/lecture/GRV2q/binary-trees)

  -  [Tree Height Remark (video)](https://www.coursera.org/learn/data-structures/supplement/S5xxz/tree-height-remark)

  -  [기본 연산들 (영상)](https://www.coursera.org/learn/data-structures/lecture/0g1dl/basic-operations)

  -  [완전 이진 트리 (영상)](https://www.coursera.org/learn/data-structures/lecture/gl5Ni/complete-binary-trees)

  -  [의사 코드(Pseudocode) (영상)](https://www.coursera.org/learn/data-structures/lecture/HxQo9/pseudocode)

  -  [힙 정렬 - 시작하기 (영상)](https://youtu.be/odNJmw5TOEE?list=PLFDnELG9dpVxQCxuD-9BSy2E7BWY3t5Sm&t=3291)

  -  [힙 정렬 (영상)](https://www.coursera.org/learn/data-structures/lecture/hSzMO/heap-sort)

  -  [힙 만들기 (영상)](https://www.coursera.org/learn/data-structures/lecture/dwrOS/building-a-heap)

  -  [MIT: 힙과 힙 정렬 (영상)](https://www.youtube.com/watch?v=B7hVxCmfPtM&index=4&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb)

  -  [CS 61B Lecture 24: 우선순위 큐 (영상)](https://archive.org/details/ucberkeley_webcast_yIUFT6AKBGE)

  -  [선형 시간에 힙 만들기 (max-heap)](https://www.youtube.com/watch?v=MiyLo8adrWw)

  -  

    max-heap 구현하기:

    -  insert

    -  sift_up - `insert` 하려면 필요

    -  get_max - 최대 원소를 반환하되, 삭제는 하지 않는다.

    -  get_size() - 저장된 원소들의 개수를 반환

    -  is_empty() - 힙에 원소를 하나도 없는 지 반환

    -  extract_max - 최대 원소를 반환하고, 그걸 삭제한다.

    -  sift_down - `extract_max` 하려면 필요하다

    -  remove(x) - x번째 원소를 삭제

    -  heapify - 배열에 있는 원소들로 힙을 만든다. `heap_sort` 하려면 필요

    -  

      heap_sort() - 정렬되지 않은 배열을 받아서 정렬된 배열로 만든다. 추가 메모리 없이 제자리에서 max-heap을 사용한다.

      - 노트: min-heap을 사용하면 연산을 줄일 수 있지만, 공간이 두 배로 필요합니다. (제자리에서 못 하기 때문에)