# db중심 설계의 문제점
- 이 프로젝트는 db중심으로 설계되어있고 다음과 같은 문제가 있다
- order.getMember()가 안 된다
  - order.getId()한 뒤 
  - em.find(Member.class,order.getId())이런 식으로 Member를 찾아야 함

# jpashop2
- db중심 설계의 문제점 해소
- jpa의 매핑 사용