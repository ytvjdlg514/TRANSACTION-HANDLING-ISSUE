FROM openjdk:11-jdk

# 컨테이너 내 작업 디렉토리
WORKDIR /app

# 로컬 프로젝트 파일들을 컨테이너로 복사
COPY . /app

# 권한 주지 않으면 오류 발생
# /bin/sh: 1: ./gradlew: Permission denied
RUN chmod +x gradlew

# 이미지 빌드 시 실행. 이미지에 적용된 상태로 저장.
# 그레이들을 이용해 빌드, 테스트는 제외 
RUN ./gradlew build 

# 컨테이너 실행 시 실행
CMD ["java", "-jar", "/app/build/libs/springDataPlay-0.0.1-SNAPSHOT.jar"]



