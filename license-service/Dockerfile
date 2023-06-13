FROM eclipse-temurin:17.0.7_7-jre-alpine

WORKDIR /app

COPY target/license-service-1.0.0.jar .

ENTRYPOINT ["java", "-jar", "license-service-1.0.0.jar"]