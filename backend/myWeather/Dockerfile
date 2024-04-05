FROM openjdk:17-jdk-alpine as builder
WORKDIR /app/myWeather
COPY ./myWeather   /app/myWeather
RUN  /app/myWeather/gradlew clean build && cp -f  /app/myWeather/build/libs/myWeather-0.0.1-SNAPSHOT.jar /app/myWeather-0.0.1-SNAPSHOT.jar
run  rm -r /app/myWeather

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/myWeather-0.0.1-SNAPSHOT.jar .
EXPOSE 8095

CMD  java -jar myWeather-0.0.1-SNAPSHOT.jar