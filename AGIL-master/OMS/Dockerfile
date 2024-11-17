FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} oms.jar

EXPOSE 8086

# Run the application
ENTRYPOINT ["java", "-jar", "/oms.jar"]
