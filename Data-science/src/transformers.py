import numpy as np
from sklearn.base import BaseEstimator, TransformerMixin
from .text_preprocessing import full_clean


class TextCleaner(BaseEstimator, TransformerMixin):
    """
    Transformer personalizado para limpieza de texto.
    Compatible con:
    - Pipelines de sklearn
    - GridSearchCV
    - joblib
    - ejecución paralela (n_jobs != 1)
    """

    def fit(self, X, y=None):
        return self

    def transform(self, X):
        """
        X: array-like o DataFrame con una sola columna de texto
        """
        # Convertimos a array 1D
        X = np.asarray(X).ravel()

        # Aplicamos limpieza
        return np.array([full_clean(text) for text in X])
