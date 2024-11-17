FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} cms.jar

EXPOSE 8084

# Run the application
ENTRYPOINT ["java", "-jar", "/cms.jar"]
