# Data Science – SentimentAPI

Este módulo corresponde al área de **Data Science** del proyecto **SentimentAPI**, desarrollado durante el Hackathon de **No Country (ONE II – Latam)**.

Aquí se implementa el **modelo de análisis de sentimientos** encargado de procesar feedbacks de usuarios mediante técnicas de **NLP (Natural Language Processing)**.

---

## Objetivo del módulo

Desarrollar un sistema de Procesamiento de Lenguaje Natural (NLP) capaz de clasificar de forma automatizada la polaridad de las opiniones de los usuarios, optimizando la precisión mediante la comparación de modelos estadísticos.

**Objetivos Específicos:**

- Procesamiento de Datos: Implementar un pipeline de limpieza y normalización de texto robusto (remoción de ruido, stopwords y normalización) utilizando transformadores personalizados.

- Análisis Predictivo: Entrenar y evaluar dos modelos de clasificación (Logistic Regression y Naive Bayes) para categorizar sentimientos en tres clases: Positivo, Negativo y Neutro.

---

## Flujo de trabajo

1. Preparación y limpieza de datos
2. Análisis exploratorio de datos
3. Entrenamiento del modelo
4. Evaluación de métricas
5. Exposición del modelo mediante un microservicio

---

## Estructura del módulo

```text
Data-science/
├── data/                 # Datsets raw y clean (Archivos ignorados en Git)
├── models/               # Modelo exportado en formato .joblib
├── notebooks/            # Experimentación y análisis
│   ├── 1_cleaning.ipynb    
│   ├── 2_EDA.ipynb         
│   └── 3_model.ipynb       
├── src/                  # Módulos de soporte (.py)
│   ├── test_preprocessing.py   # Funciones de normalización y limpieza
│   ├── labelling.py            # Lógica de etiquetado de sentimientos
│   ├── transformers.py         # Transformer personalizado (Clase TextCleaner)
│   └── __init__.py             # Inicializador de paquete
|
├── app.py                # microservicio para consumo del modelo por el backend
└── requirements.txt      # Dependencias del proyecto
```

**Notas sobre el almacenamiento de archivos**

>**Carpeta data/:** Debido a las políticas de tamaño de GitHub, esta carpeta se encuentra vacía en el repositorio remoto. Para reproducir el proyecto, se requiere colocar localmente el dataset original en esta ruta (ver la sección de [Instalación y uso](#instalación-y-uso)). El notebook de limpieza generará automáticamente la versión procesada en esta misma ubicación.

---

## Componentes principales

1. **Procesamiento de Texto (src/)**

    Para asegurar la modularidad, se crearon scripts específicos para el tratamiento de datos:

    - `test_preprocessing.py`: Contiene la lógica de normalización y remoción de stopwords. Incluye la función `full_clean` que une los pasos para la limpieza de datos.

    - `labelling.py`: Lógica de etiquetado basada en el score original del dataset (Positivo/Negativo/Neutro).

    - `transformers.py`: Contiene la clase `TextCleaner`. Este es un Transformer personalizado que permite integrar la limpieza de texto directamente en un Pipeline de Scikit-Learn, facilitando el despliegue y evitando el data leakage.

2. Notebooks
    - `1_cleaning.ipynb`: Etapa de limpieza. Transformación del dataset crudo y aplicación de etiquetas.

    - `2_EDA.ipynb`: Análisis exploratorio para entender la distribución de clases.

    - `3_model.ipynb`: Entrenamiento de modelos de Logistic Regression y Naive Bayes y comparación de métricas.
    
        Por temas de tiempo de ejecución, en el notebook solo están considerados los hiperparámetros que mostraron el mejor rendimiento, mostrado como un F-score más alto. Los hiperparámetros considerados para cada modelo fueron:
        
        - Naive Bayes:

            a) *Número máximo de palabras que usa el vectorizador:* 3000, 5000, 7000.

            b) *Tamaño de los n-gramas:* unigramas (1,1) o bigramas (1,2).

            c) *Suavización de Laplace de Naive Bayes:* 0.1, 0.5, 1.


        - Regresión logística:

            a) *Número máximo de palabras que usa el vectorizador:* 3000, 5000, 7000.

            b) *Tamaño de los n-gramas:* unigramas (1,1) o bigramas (1,2).

            c) *Parámetro C de Regresión logística:* 0.1, 1, 10.    
    

---

## Resultados

- Comparativa entre modelos (Naive Bayes vs. Regresión Logística):


    | Modelo | F1-Score | Nota |
    | :--- | :---: | :--- |
    | **Logistic Regression** | **0.65** | Mejor rendimiento general |
    | Naive Bayes | 0.48 | Más rápido en entrenamiento |
    

- Detalle de métricas del modelo de Regresión Logística:


    | Clase | Precision | Recall | F1-Score |
    | :--- |:---: | :---: | :---: |
    | Negativo| 0.64 | **0.75** | 0.69 |
    | Neutral | **0.27** | 0.63 | 0.38 |
    | Positivo | **0.97** | 0.81 | 0.88 |
---

## Instalación y uso

1. **Clonar el repositorio:**

    ```bash
    git clone https://github.com/eleuteriovargas/HackathonG8Equipo25.git
    ```

2. **Crear un entorno virtual:**

    ```bash
    python -m venv venv
    source venv/bin/activate
    ```

3. **Instalar dependencias:**

    ```bash
    pip install -r requirements.txt
    ```

4. **Preparar los datos:** 

    Debido al tamaño de los archivos, la carpeta `data/` está vacía en el repositorio remoto. 
    
    Se debe descargar el dataset desde el siguiente [link](https://www.kaggle.com/datasets/snap/amazon-fine-food-reviews) y colocar el archivo con el nombre `1_raw_data.csv` en la carpeta `data/`.

5. **Ejecutar los Notebooks:** 

    Se debe seguir el orden: `1_cleaning.ipynb` -> `2_EDA.ipynb` -> `3_model.ipynb`. 
    
    El modelo con mejor desempeño se guardará automáticamente en formato `.joblib` en la carpeta `models/`.

6. **Ejecutar el microservicio:**

    Si se quiere ejecutar el microservicio, en la terminal, ubicarse en la carpeta Data-science y ejecutar el siguiente comando:

    ```bash
    python -m uvicorn app:app --host 0.0.0.0 --port 8000
    ```
    
    Ir al navegador mientras la aplicación está corriendo e ingresar:

    ```bash
    http://127.0.0.1:8000/docs
    ```
    Realizar pruebas de la aplicación con comentarios en inglés. El input debe estar en formato JSON.

---

## Tecnologías y conceptos

- **Área:** Natural Language Processing (NLP).
- **Modelos:** Regresion Logística, Naive Bayes Multinomial
- **Librerías de python:**
  * `Scikit-Learn` (Modelamiento y Pipelines) 
  * `Pandas` y `Numpy` (Manipulación de datos)
  * `NLTK` (vectorización de texto)
  * `Joblib` (exportación del modelo)
  * `FastApi` (microservicio)
- **Jupyter Notebooks**

---

## Notas
Este módulo se integra con el Backend mediante HTTP y JSON.

El enfoque prioriza claridad, reproducibilidad y escalabilidad.

El modelo puede ser mejorado o reemplazado sin afectar al Backend.