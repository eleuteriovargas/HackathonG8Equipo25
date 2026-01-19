#  SentimentAPI

**SentimentAPI** es un proyecto desarrollado durante un **Hackathon de No Country (ONE II – Latam)**.  
El objetivo es analizar el **sentimiento de feedbacks de usuarios** y transformarlos en informacion util para la toma de decisiones empresariales.

El proyecto integra un **Backend en Java (Spring Boot)** con un modulo de **Data Science** encargado del analisis de sentimientos mediante tecnicas de **NLP (Natural Language Processing)**.

---

## Objetivo del proyecto

Permitir a empresas y organizaciones:

- Analizar comentarios de clientes o usuarios
- Clasificar el sentimiento en **positivo, negativo o neutral**
- Obtener un **score de sentimiento**
- Automatizar el análisis de grandes volumenes de feedback

---

## Arquitectura general

El repositorio está organizado de forma modular para facilitar el trabajo colaborativo entre Backend y Data Science.

```
sentiment-api/
 ├─ backend/        → API REST (Spring Boot)
 ├─ data-science/   → Modelo de análisis de sentimientos
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
- Swagger / OpenAPI
- Docker (opcional)

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
- FastAPI o Flask
- Jupyter Notebooks

**Responsabilidades:**
- Preparacion y analisis de datos
- Entrenamiento del modelo
- Evaluacion de resultados
- Exposicion del modelo mediante una API
- Documentacion del enfoque de ML

📁 Ubicación: `data-science/`

---

## Ejemplo de uso

### Request
```json
{
  "text": "El servicio fue rápido y muy eficiente",
	"idioma": "pt"
}
```

### Response
```json
{
  "text": "El servicio fue rápido y muy eficiente",
  "sentiment": "POSITIVE",
  "score": 0.92
}
```

---

## Equipo

Proyecto desarrollado por un equipo multidisciplinario compuesto por:

- **4 Backend Developers**
- **7 Data Science Developers**

El trabajo se realiza de manera colaborativa utilizando:
- GitHub
- Pull Requests
- Buenas practicas de control de versiones

---

## Contexto del proyecto

Este proyecto fue desarrollado como parte de un **Hackathon organizado por No Country**, enfocado en:

- Trabajo en equipo
- Resolucion de problemas reales
- Aplicacion de conocimientos tecnicos
- Crecimiento profesional en el sector tecnologico

---

## Mejoras futuras

- Autenticacion y autorizacion
- Persistencia de resultados
- Dashboard de visualizacion de metricas
- Soporte multi-idioma
- Mejora del modelo de NLP
- Integracion con sistemas empresariales

---


