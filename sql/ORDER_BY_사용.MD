- ### 긴 속성을 n으로 (n의 의미 = 번 째 속성) 대체하여 사용
```sql
-- order by에 속성명 대신 번호를 쓸 수있다 (Age로 치환된 age 기준 정렬)
select
code, name, age Age
from country
where age between 10 and 20
order by 3 desc;
```


- ### 여러 기준으로 정렬하기
```sql
-- 여러 기준으로 정렬하기. 앞에 있을 수록 우선순위가 높은 기준이다.
select
	code, name, age
from
	country
where age in (800,1800,1811,1901)
order by age asc, code desc;
```