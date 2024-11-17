FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} apigateway.jar

EXPOSE 8000

# Run the application
ENTRYPOINT ["java", "-jar", "/apigateway.jar"]
