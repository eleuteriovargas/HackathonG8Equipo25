# 1. Definir la imagen base primero
FROM eclipse-temurin:21-jre-alpine

# 2. Instalar las librerías necesarias para que funcione HTTPS hacia Render
RUN apk add --no-cache libgcc libstdc++

# 3. Configurar el directorio de trabajo
WORKDIR /app

# 4. Copiar tu aplicación
COPY Backend/SentimentAPI/target/app.jar app.jar

# 5. Puertos y ejecución
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]