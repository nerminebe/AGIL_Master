FROM eclipse-temurin:17-jre-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} ums.jar

EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/ums.jar"]
