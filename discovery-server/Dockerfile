FROM eclipse-temurin:17.0.7_7-jre-alpine

WORKDIR /app

COPY target/discovery-server-1.0.0.jar .

ENTRYPOINT ["java", "-jar", "discovery-server-1.0.0.jar"]