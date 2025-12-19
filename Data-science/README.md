# Data Science – SentimentAPI

Este módulo corresponde al área de **Data Science** del proyecto **SentimentAPI**, desarrollado durante el Hackathon de **No Country (ONE II – Latam)**.

Aquí se implementa el **modelo de análisis de sentimientos** encargado de procesar feedbacks de usuarios mediante técnicas de **NLP (Natural Language Processing)**.

---

## Objetivo del módulo

- Analizar texto en lenguaje natural
- Clasificar sentimientos en:
  - Positivo
  - Negativo
  - Neutral
- Generar un score de sentimiento
- Exponer el modelo mediante una API para su consumo por el Backend

---

## Tecnologías utilizadas

- Python
- NLP (Natural Language Processing)
- Machine Learning
- FastAPI o Flask
- Jupyter Notebooks

---

## Flujo de trabajo

1. Preparación y limpieza de datos
2. Entrenamiento del modelo
3. Evaluación de métricas
4. Exposición del modelo mediante una API
5. Integración con el Backend

---

## API de análisis (ejemplo)

### Request
```json
{
  "text": "La atención fue muy mala"
}
```
---

###  Response
```json
Copiar código
{
  "sentiment": "NEGATIVE",
  "score": -0.81
}
```

## Ejecución (ejemplo)
uvicorn app.main:app --reload

---

## Notas
Este módulo se integra con el Backend mediante HTTP y JSON.

El enfoque prioriza claridad, reproducibilidad y escalabilidad.

El modelo puede ser mejorado o reemplazado sin afectar al Backend.