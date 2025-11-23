#from gradle:latest as builder
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build -x test
# 이쯤에 jar 파일이 (패키지) 생성된다.

FROM eclipse-temurin:17-jdk
WORKDIR /app
ARG JAR_FILE=/app/build/libs/ex0930_compose-0.0.1-SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} /docker_0930.jar

ENTRYPOINT [ "java","-jar", "/docker_0930.jar" ]