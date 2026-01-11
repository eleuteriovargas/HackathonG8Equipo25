import re
import numpy as np
from sklearn.feature_extraction.text import ENGLISH_STOP_WORDS

# Stopwords de scikit-learn
STOP_WORDS_EN = set(ENGLISH_STOP_WORDS)


def normalize_text(text):
    """
    Normaliza un texto:
    - Maneja NaN / None
    - Convierte a minúsculas
    - Elimina URLs
    - Elimina números y símbolos
    - Elimina espacios extra
    """
    if text is None or (isinstance(text, float) and np.isnan(text)):
        return ""

    text = text.lower()
    text = re.sub(r"http\S+", "", text)
    text = re.sub(r"[^a-z\s]", "", text)
    text = re.sub(r"\s+", " ", text).strip()
    return text


def remove_stopwords(text, stop_words=STOP_WORDS_EN):
    """
    Elimina stopwords de un texto.
    """
    return " ".join(
        word for word in text.split()
        if word not in stop_words
    )


def full_clean(text):
    """
    Limpieza completa del texto.
    Se usa directamente en el pipeline de sklearn.
    """
    text = normalize_text(text)
    text = remove_stopwords(text)
    return text
