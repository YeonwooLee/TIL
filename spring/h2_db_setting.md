```
# DATABASE
#h2 콘솔 접속 허용할지 여부
spring.h2.console.enabled=true
# h2 콘솔 접속 url
spring.h2.console.path=/h2-solved-group
# db접속 url, ~는 c:user/현재 사용자
spring.datasource.url=jdbc:h2:~/solved-group
# db접속에 사용하는 드라이버
spring.datasource.driverClassName=org.h2.Driver
# db 계정
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.format_sql=true
```