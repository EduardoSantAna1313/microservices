# syntax=docker/dockerfile:1
FROM amazoncorretto:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} naming_app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/naming_app.jar"]
