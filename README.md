# Gradle Wrapper 생성하는 방법

```
gradle wrapper --gradle-version 7.6
```

명령어 실행 시 .gradle과 gradle, gradlew, gradle.bat이 생성된다

# curl을 병렬로 여러번 호출

```
seq 1 3 | xargs -I $ -n1 P10 curl "http://localhost:8080/nested"
```

seq 1 3

- 1부터 3까지 숫자를 출력

xargs -I $ -n1 -P10 curl "http://localhost:8080/nested"

- xargs는 입력값을 받아서 명령어로 실행시켜줌

- -I $ 입력값을 \$로 대체함

- -n1 각 숫자마다 한 번 실행

- -P10 동시에 최대 **10개 프로세스(병렬)** 로 실행

# 자바 쓰레드 덤프 보기

- 컨테이너 안의 Java PID 확인

```
docker exec -it transaction-handling-issue-app-1 ps aux | grep java
```

PID가 1일 떄가 많다

- 덤프 출력하기

```
docker exec -it transaction-handling-issue-app-1 kill -3 1
```

# 문제 발생 상황

| 항목                        | 값                              |
| --------------------------- | ------------------------------- |
| 최대 커넥션 수              | 3                               |
| 필요한 커넥션 수 (3개 요청) | 6                               |
| 부족한 커넥션 수            | 3                               |
| 타임아웃 시간               | 6000ms                          |
| 결과                        | 커넥션 풀 부족으로 대기 후 실패 |

- 2번만 호출할 경우 대기 후 정상 종료 된다

# 주의

- Hikari는 딱 1 개의 커넥션 풀(HikariPool-1)만 생성하고 풀 내부에서 최대 3개의 DB 커넥션을 관리한다
