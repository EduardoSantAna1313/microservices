FROM amazoncorretto:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} cambio_app.jar

RUN bash -c 'touch /cambio_app.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar" , "/cambio_app.jar"]
