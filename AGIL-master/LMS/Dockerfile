FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} lms.jar

EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "/lms.jar"]
