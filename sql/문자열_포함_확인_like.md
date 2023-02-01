```sql
-- 10 문자열 검색 기준 1. 무엇이든 상관없어요(%), 2. 글자수 길이 고정(_)
select
	code, name, hobby
from country
where
	hobby like '%구%'
    and name like '_o%'; --2번째 글자가 o
    
```