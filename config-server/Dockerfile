FROM eclipse-temurin:17.0.7_7-jre-alpine

WORKDIR /app

COPY target/config-server-1.0.0.jar .

ENTRYPOINT ["java", "-jar", "config-server-1.0.0.jar"]