FROM openjdk:17-jdk-alpine as builder
WORKDIR /myWeather
CMD ["./gradlew", "clean", "build"]
COPY ./myWeather/build/libs/myWeather-0.0.1-SNAPSHOT.jar   /app/myWeather-0.0.1-SNAPSHOT.jar 

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/myWeather-0.0.1-SNAPSHOT.jar .
EXPOSE 8095

CMD  java -jar myWeather-0.0.1-SNAPSHOT.jar