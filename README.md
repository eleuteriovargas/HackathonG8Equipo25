#  SentimentAPI

**SentimentAPI** es un proyecto desarrollado durante un **Hackathon de No Country (ONE II – Latam)**.  
El objetivo es analizar el **sentimiento de feedbacks de usuarios** y transformarlos en información útil para la toma de decisiones empresariales.

El proyecto integra un **Backend en Java (Spring Boot)** con un módulo de **Data Science** encargado del análisis de sentimientos mediante técnicas de **NLP (Natural Language Processing)**.

---

## Objetivo del proyecto

Permitir a empresas y organizaciones:

- Analizar comentarios de clientes o usuarios
- Clasificar el sentimiento en **positivo, negativo o neutral**
- Obtener un **score de sentimiento**

---

## Arquitectura general

El repositorio está organizado de forma modular para facilitar el trabajo colaborativo entre Backend y Data Science.

```
sentiment-api/
 ├─ backend/            → API REST (Spring Boot)
 ├─ data-science/       → Modelo de análisis de sentimientos
 ├─ docker-compose.yml  → Desplegar microservicios en la nube
 └─ README.md
```

### Flujo de funcionamiento

1. El usuario envía un texto a la API Backend
2. El Backend valida la entrada
3. El Backend se comunica con el servicio de Data Science
4. El modelo analiza el sentimiento del texto
5. La API responde con la clasificación y el score

---

## Backend

**Tecnologías utilizadas:**
- Java
- Spring Boot
- REST API
- Maven
- Docker

**Responsabilidades:**
- Exponer endpoints REST
- Validar datos de entrada
- Consumir el servicio de Data Science
- Manejar errores y respuestas
- Documentar la API

📁 Ubicación: `backend/`

---

## Data Science

**Tecnologías utilizadas:**
- Python
- NLP (Natural Language Processing)
- Machine Learning
- FastAPI
- Jupyter Notebooks

**Responsabilidades:**
- Preparación y análisis de datos
- Entrenamiento del modelo
- Evaluación de resultados
- Exposición del modelo mediante un microservicio
- Documentación del enfoque de ML

📁 Ubicación: `data-science/`

---

## Ejemplo de uso

### Request
```json
{
  "text": "El servicio fue rápido y muy eficiente"
}
```

### Response
```json
{
  "sentimient0": "POSITIVE",
  "probabilidad": 0.92
}
```

---

## Equipo

Proyecto desarrollado por un equipo multidisciplinario compuesto por:

- **4 Backend Developers**
- **6 Data Science Developers**

El trabajo se realizó de manera colaborativa utilizando:
- GitHub
- Pull Requests
- Buenas prácticas de control de versiones

---

## Contexto del proyecto

Este proyecto fue desarrollado como parte de un **Hackathon organizado por No Country**, enfocado en:

- Trabajo en equipo
- Resolución de problemas reales
- Aplicación de conocimientos técnicos
- Crecimiento profesional en el sector tecnológico

---

## Mejoras futuras

- Autenticación y autorización
- Persistencia de resultados
- Dashboard de visualización de métricas
- Soporte multi-idioma
- Mejora del modelo de NLP
- Integración con sistemas empresariales
- Análisis de comentarios en lote
- Análisis para detección de sarcasmo

---


