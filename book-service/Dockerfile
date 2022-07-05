# syntax=docker/dockerfile:1
FROM amazoncorretto:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} book_app.jar

ENTRYPOINT ["java","-jar","/book_app.jar"]
