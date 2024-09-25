FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/find-nth-max-service.jar /app/find-nth-max-service.jar

ENTRYPOINT ["java", "-jar", "find-nth-max-service.jar"]
