# syntax=docker/dockerfile:1
FROM amazoncorretto:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} gateway_app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/gateway_app.jar"]
