# Utilisez l'image de base de Java
FROM openjdk:17-jdk-slim

# Créez un répertoire pour l'application
WORKDIR /app

# Copiez le fichier JAR de l'application dans le conteneur
COPY target/Config_server-0.0.1-SNAPSHOT.jar /app/Config_server.jar

# Exposer le port utilisé par le Config Server
EXPOSE 8888

# Lancer l'application
ENTRYPOINT ["java", "-jar", "/Config_server.jar"]
