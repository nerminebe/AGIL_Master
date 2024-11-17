FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} eureka.jar

EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "/eureka.jar"]
