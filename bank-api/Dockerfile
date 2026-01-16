FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build/libs/bank-api-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]