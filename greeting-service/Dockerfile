# syntax=docker/dockerfile:1
FROM amazoncorretto:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} greeting-app.jar

ENTRYPOINT ["java","-jar","/greeting-app.jar"]
