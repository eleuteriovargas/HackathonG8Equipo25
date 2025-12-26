# Dockerfile

#Imagen base con Java 21 (LTS)
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el JAR desde la subcarpeta /
COPY SentimentAPI/target/*.jar app.jar

# Puerto expuesto
EXPOSE 8080

# Comando que ejecuta el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]