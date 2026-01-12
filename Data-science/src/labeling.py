def map_sentiment(score):
    """
    Mapea un score numérico a una etiqueta de sentimiento.

    SCORE → SENTIMIENTO
    4–5   → Positive
    3     → Neutral
    1–2   → Negative
    """
    if score >= 4:
        return "Positive"
    elif score <= 2:
        return "Negative"
    else:
        return "Neutral"