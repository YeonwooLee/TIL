#### * db안에 있는 데이터는 db에서 만들어서 꺼내는 게 빠르다.

#### #데이터 저장
메모리 -> 파일 -> 디비

is null 고려
use world; # 월드 사용

SELECT DATEDIFF('2017/08/25', '2011/08/25') AS DateDiff;

#현재 날짜와 올해가 몇 일이 지났는지, 100일 후는 몇일인지 출력하시오
select 
	date_format(now(),'%Y-%m-%d') 오늘,
    datediff(date(now()),	date('2023-01-01')) '올해 지난 날',
    date_format(date_add(now(),interval 100 day),'%Y-%m-%d') '100일 후'
    from dual;


select * from nation;
#nation에러 asia에 있는 나라 중 희망 수명이 있는 경우에 기대 수명이
#80초과면 장수국가, 60초과면 일반국가, 그렇지 않으면 단명국가라고 표현하시오
# 기대수명으로 정렬한다
select name, continent, energyUse,
case
	when energyUse>80 then '장수국가'
    when energyUse>60 then '일반국가'
    else '단명국가'
 end "구분"
from nation
where continent = 'Asia'
order by 3;


#nation에서 (power-powerold)를 power 향상이라고 표현하시오. 단 powerold가 없는 경우
# 신규라고 출력하고 name으로 정렬한다(239건)
select name, power, powerold,ifnull(power-powerold,'신규') 'power 향상'
from nation
order by name asc;


#2020년 어린이 날이 평일이면 행복, 토요일 또는 일요일이면 불행이라고 출력하시오
select weekday('2020-05-05'),
case
	when (weekday('2020-05-05')=6 or weekday('2020-05-05')=5 )then '불행'
    else '행복'
    end "행복여부"
from dual;


#nation에서 전체 자료의 수와 독립 연도가 있는 자료의 수를 각각 출력하시오
select 
	(select count(*) from nation) 전체,
    (select count(*) from nation
		where not isnull(IndepYear)) '독립 연도 보유';
        

#nation에서 기대 수명의 합계, 평균, 최대, 최소를 출력하시오 평균은 소수점 2자리로 반올림한다.
select
	sum(energyUse) as '합계',
    avg(energyUse) as '평균',
    max(energyUse) as '최대',
    min(energyUse) as '최소'
from nation;


#nation에서 continent 별 국가의 개수와 인구의 합을 구하시오 국가 수로 정렬처리한다(7건)
select
	continent,
    count(*) '국가 수',
    sum(population) '인구 합'
from nation
group by continent
order by count(*) desc;

#nation에서 대륙별 국가 표면적의 합 구함. 면적의 합으로 내림차순 정렬하고 상위 3건만 출력한다.
select continent, sum(surfacearea) '표면적 합'
from nation 
group by continent
order by 2 desc
limit 3;


#nation에서 대륙별로 인구가 50,000,000 이상인 나라의 power 총 합을 구하시오.
#합으로 오름차순 정렬한다(5건)
select 
	continent, 
    sum(power) 'power 합'
from nation 
where population >= 50000000
group by continent
order by 2 asc;


#nation에서 !대륙별로 인구가 50,000,000이상!인 나라의 power 총 합을 구하시오.
# 이때 power의 합이 5,000,000이상인 것만 구하시오
select 
	continent,
	sum(power) 'power 합'
from nation
where population >= 50000000
group by continent
having sum(power) >= 5000000;

#nation에서 연도별로 10개이상의 나라가 독립한 해는 언제입니까?
select indepyear, count(indepyear) "독립 국가 수"
from nation
group by indepyear
having count(indepyear) >= 10
order by 2 desc;

#nation에서 국가별로 power와 함께 전 세계 평균 power, 대륙 평균 power를 구하시오
#집계 함수의 over 절은 grouping 대상을 지정하며 여러 행에 표시될 수 있다.
select continent, name, power,
avg(power) over() "전세계 평균",
avg(power) over(partition by continent) "대륙 평균"
from nation;