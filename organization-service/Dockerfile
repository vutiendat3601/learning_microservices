FROM eclipse-temurin:17.0.7_7-jre-alpine

WORKDIR /app

COPY target/organization-service-1.0.0.jar .

ENTRYPOINT ["java", "-jar", "organization-service-1.0.0.jar"]