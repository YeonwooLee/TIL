```sql
-- instr({문자열},{찾는문자열})
select code, name, hobby
from country
where instr(hobby ,'구') -- hobby에 '구'가 포함되어있나 판단
```