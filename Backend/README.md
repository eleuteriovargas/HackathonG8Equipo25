#  Backend – SentimentAPI

Este módulo corresponde al **Backend** del proyecto **SentimentAPI**, desarrollado durante el Hackathon de **No Country (ONE II – Latam)**.

El Backend es responsable de exponer una **API REST** que recibe feedbacks de usuarios, valida la información y se comunica con el módulo de **Data Science** para obtener el análisis de sentimientos.

---

## Responsabilidades

- Exponer endpoints REST
- Validar datos de entrada
- Consumir el servicio de análisis de sentimientos
- Manejar respuestas y errores
- Documentar la API
- Facilitar la integración con otros sistemas

---

## Tecnologías utilizadas

- Java
- Spring Boot
- Maven
- REST API
- Swagger / OpenAPI
- Docker (opcional)

---

## Endpoint principal (ejemplo)

### Request
```json
{
  "text": "El servicio fue rápido y muy eficiente"
}
```

---

## Integración con Data Science

El Backend se comunica con el módulo de Data Science mediante una API REST, enviando el texto a analizar y recibiendo la clasificación del sentimiento y su score.

---

## Ejecución (ejemplo)
```bash
mvn spring-boot:run
```
---

## Notas

- **Este módulo forma parte de un proyecto colaborativo.**

- **La estructura sigue buenas prácticas para facilitar el mantenimiento y escalabilidad.**

---

##  Requisitos previos para Docker

Antes de levantar el servicio, asegúrate de tener instalado:

- **Docker Desktop**
- **Docker Compose**
- **Git**
- **Java 21** (solo si se ejecuta sin Docker)

---

## Verificar instalación de Docker:

```bash
docker --version
docker compose version
```

---

## Levantar el servicio con Docker (recomendado)


- **Clonar el repositorio**
```bash
git clone <URL_DEL_REPOSITORIO>
cd HackathonG8Equipo25/Backend
```

- **Construir la imagen del Backend**
```bash
docker build -t sentiment-api .
```

Esto generará la imagen del servicio backend de manera local.

- **Levantar los servicios con Docker Compose**
```bash
docker compose up -d
```

### Servicios disponibles:

- **Backend (Sentiment API) → http://localhost:8080**

- **MySQL → puerto 3306**

- **Verificar contenedores activos:**
```bash
docker ps
```

### Probar el servicio
```bash
Ejemplo de petición:

POST http://localhost:8080/sentiment
Content-Type: application/json
```

```json
{
  "text": "El servicio fue excelente y muy rápido"
}
```

### Respuesta esperada:
```json
{
  "sentiment": "POSITIVE",
  "confidence": 0.95
}
```

### Detener los servicios
```bash
docker compose down
```

---

## Levantar el servicio sin Docker (modo desarrollo)

### Entrar al módulo Backend
```bash
cd Backend/SentimentAPI
```
### Ejecutar la aplicación

En sistemas Unix / MacOS:
```bash
./mvnw spring-boot:run
```

En Windows:
```bash
mvnw.cmd spring-boot:run
```

---

### El servicio quedará disponible en:
```bash
http://localhost:8080
```

### Ejecutar pruebas

Para ejecutar las pruebas unitarias y de integración:
```text
./mvnw test
```

---
 
## Logs

El manejo de logs del Backend está configurado mediante el archivo:

```text
src/main/resources/application.properties
```
### Configuración básica de logs

Ejemplo de configuración:

```bash.
# Nivel global de logs
logging.level.root=INFO

# Nivel de logs del paquete del proyecto
logging.level.com.sentiment=DEBUG

# Formato de logs en consola
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

Esta configuración permite:

- ***Visualizar logs en consola durante el desarrollo***
- ***Ajustar niveles de detalle sin recompilar la aplicación***
- ***Facilitar el diagnóstico de errores y monito***
