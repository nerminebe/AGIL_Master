FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} mailing.jar

EXPOSE 8085

# Run the application
ENTRYPOINT ["java", "-jar", "/mailing.jar"]
