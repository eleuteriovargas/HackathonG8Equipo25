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
mvn spring-boot:run

---

## Notas

Este módulo forma parte de un proyecto colaborativo.

La estructura sigue buenas prácticas para facilitar el mantenimiento y escalabilidad.





