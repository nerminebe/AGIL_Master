FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} pms.jar

EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "/pms.jar"]
