#### #gradle

```java
dependencies {
    //springboot starter도 있어야함
    testImplementation 'org.awaitility:awaitility:3.1.2' //얘가 스케쥴러용 dependency
}
```





#### #Application에 `EnableScheduling` 추가

```java
@SpringBootApplication
@EnableScheduling
public class StockHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockHelperApplication.class, args);
	}

}

```



#### #간단 작업 예시

```java
@Component//컴포넌트 등록
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    //의존성 주입
    private final StockFinder stockFinder;
    private final TelegramBotsApi telegramBotsApi;
    private final EchoBot echoBot;
    private final MyConverter myConverter;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	//@Scheduled(fixedRate = 5000) 일케쓰면 5초마다
    @Scheduled(cron = "*/10 55 1,19 * * *") //1시 17시의 55분에 10초마다 발생
    public void reportCurrentTime() {
        // stockFinder.setTempStock(stockFinder.getStocks());
        log.info("주식리스트 갱신 {}", dateFormat.format(new Date()));
        //TODO db에 주식 리스트 갱신 현재시간+주식코드 = pk, 주식명, 현재가, 등수 등
    }
```





참고 사이트 : https://spring.io/guides/gs/scheduling-tasks/

스케쥴 시간 표현식 : https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronExpression.html