FROM eclipse-temurin:17.0.7_7-jre-alpine

WORKDIR /app

COPY target/api-gateway-1.0.0.jar .

ENTRYPOINT ["java", "-jar", "api-gateway-1.0.0.jar"]