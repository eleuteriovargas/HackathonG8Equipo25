import joblib
import numpy as np
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List

# 1. Configuración y Carga del Modelo
# El pipeline ya sabe cómo tratar el texto gracias a que guardaste 
# el vectorizador y el modelo juntos.
MODEL_PATH = "models/sentiment_lr.joblib"

try:
    model = joblib.load(MODEL_PATH)
except Exception as e:
    raise RuntimeError(f"No se pudo cargar el modelo en {MODEL_PATH}: {e}")


# 2. Definición del Esquema de Datos (JSON Input)
class SentimentRequest(BaseModel):
    text: str

class SentimentResponse(BaseModel):
    comentario: str
    sentimiento: str
    probabilidad: float


# 3. Inicialización de FastAPI
app = FastAPI(
    title="Sentiment Analysis Service",
    description="API para clasificar sentimientos (Positivo, Negativo, Neutro) usando Regresión Logística."
)

#4. Endpoint de predicción

@app.post("/predict", response_model=SentimentResponse)
def predict(request: SentimentRequest):
    """
    Recibe un comentario, lo convierte en una Serie de Pandas para mantener
    la compatibilidad con el entrenamiento original, y devuelve la 
    clasificación junto con su probabilidad.
    """
    # 1. Validación básica de entrada
    if not request.text.strip():
        raise HTTPException(status_code=400, detail="El texto proporcionado está vacío.")

    try:
        # 1. Convertimos el texto en un DataFrame de una sola fila y una columna
        # IMPORTANTE: El nombre de la columna ('texto') debe ser el MISMO 
        # que usaste cuando entrenaste el modelo.
        import pandas as pd
        data_input = pd.DataFrame([request.text], columns=['text']) 

        # 2. Realizar la predicción
        prediction = model.predict(data_input)[0]

        # 3. Obtener las probabilidades
        probabilities = model.predict_proba(data_input)[0]
        max_probability = float(np.max(probabilities))

        return {
            "comentario": request.text,
            "sentimiento": str(prediction),
            "probabilidad": round(max_probability, 4)
        }

    except Exception as e:
        # Si algo falla (ej. versión de scikit-learn o error de tipos), 
        # devolvemos el error detallado para debuguear.
        raise HTTPException(status_code=500, detail=f"Error en la predicción: {str(e)}")

#5 Ruta de salud para verificar que el API está viva
@app.get("/health")
def health_check():
    return {"status": "ok", "model_loaded": True}