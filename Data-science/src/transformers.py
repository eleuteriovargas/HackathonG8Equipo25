import numpy as np
import pandas as pd
from sklearn.base import BaseEstimator, TransformerMixin
from .text_preprocessing import full_clean


class TextCleaner(BaseEstimator, TransformerMixin):
    """
    Transformer personalizado para limpieza de texto.
    Compatible con:
    - Pipelines de sklearn
    - GridSearchCV
    - joblib
    """

    def fit(self, X, y=None):
        return self

    def transform(self, X):
        """
        X: array-like o DataFrame con una sola columna de texto
        """
        # Convertimos a array 1D
        # 1. Se aegura que X sea iterable (list, Series o array)
        if isinstance(X, pd.DataFrame):
            X = X.iloc[:, 0]
        elif isinstance(X, np.ndarray):
            X = X.ravel()

        # 2. Se aplica limpieza devolviendo una LISTA de Python.
        return [full_clean(str(text)) for text in X]
