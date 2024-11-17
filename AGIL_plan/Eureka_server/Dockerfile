# Utilisez l'image de base de Java
FROM openjdk:17-jdk-slim

# Créez un répertoire pour l'application
WORKDIR /app

# Copiez le fichier JAR de l'application dans le conteneur
COPY target/Eureka_server-0.0.1-SNAPSHOT.jar /app/Eureka_server.jar

# Exposer le port utilisé par Eureka
EXPOSE 8761

# Lancer l'application
ENTRYPOINT ["java", "-jar", "/Eureka_server.jar"]

