# 🎯 Backend – SentimentAPI — Análisis de Sentimientos de Feedbacks

## 🌟 ¿Qué es este proyecto?

¡Hola! 👋 Bienvenido a **SentimentAPI**, un proyecto súper interesante que nació durante el Hackathon de **No Country (ONE II – Latam)**. 

Imagina que tienes una empresa y recibes decenas de comentarios de clientes cada día. ¿Te gustaría saber automáticamente si tus clientes están felices 😊, enojados 😠 o simplemente siendo neutrales 😐? ¡Pues eso es exactamente lo que hace este proyecto!

### 💡 La Idea Central

Este Backend es como un "traductor de emociones" que:
- Recibe comentarios o reseñas de clientes
- Analiza el comentario(s)
- Te dice si el sentimiento es **Positivo**, **Negativo** o **Neutral**
  
---

## 🚀 ¿Para quién es útil esto?

Este proyecto es perfecto para:

- 📞 **Equipos de Atención al Cliente**: Para identificar rápidamente a clientes insatisfechos
- 📈 **Departamentos de Marketing**: Para medir cómo responde la gente a sus campañas
- 🏢 **Dueños de negocios**: Para entender la satisfacción general de sus clientes sin leer todo manualmente
- 💻 **Desarrolladores**: Como ejemplo de cómo integrar Data Science con Backend

---

### ⚖️ Responsabilidades

- Exponer endpoints REST
- Validar datos de entrada
- Consumir el servicio de análisis de sentimientos
- Manejar respuestas y errores
- Facilitar la integración con otros sistemas

---

## ⚙️ Tecnologías utilizadas

- ☕ **Java 21**: El lenguaje de programación
- 🍃 **Spring Boot**: Framework que hace todo más fácil
- 🪶 **Maven**: Herramienta de compilación
- ☁️ **Docker**: Para que funcione igual en cualquier computadora
- 🐳 **MySQL**: Base de datos para guardar información

---

## 🕹️ ¿Cómo lo uso?

### 🐳 Opción 1: Con Docker (¡Súper fácil!)

#### Paso 1: Descargar el proyecto
```bash
git clone <URL_DEL_REPOSITORIO>
cd HackathonG8Equipo25/Backend
```

#### Paso 2: Construir la aplicación
```bash
docker build -t sentiment-api .
```
*(Esto crea una "caja" con todo lo necesario)*

#### Paso 3: ¡Arrancar todo!
```bash
docker compose up -d
```
*(El `-d` significa que corre en segundo plano)*

#### 🎉 ¡Listo! Tu API está funcionando en:
- 🌐 **API**: http://localhost:8080
- 🗄️ **Base de datos**: Puerto 3306

#### 🛑 Para detener todo:
```bash
docker compose down
```

---

### 💻 Opción 2: Sin Docker (Modo desarrollador)

#### Paso 1: Entrar a la carpeta
```bash
cd Backend/SentimentAPI
```

#### Paso 2: Ejecutar

En **Mac/Linux**:
```bash
./mvnw spring-boot:run
```

En **Windows**:
```bash
mvnw.cmd spring-boot:run
```

#### ✅ Verifica que esté funcionando en:
```
http://localhost:8080
```

---

## 🧪 Ejemplos para probar

Aquí tienes 3 ejemplos listos para copiar y pegar:

### 😊 Ejemplo 1: Comentario Positivo
```bash
curl -X POST http://localhost:8080/sentiment \
  -H "Content-Type: application/json" \
  -d '{"text": "Me encantó el producto, excelente calidad"}'
```

**Resultado esperado:**
```json
{
  "sentimiento": "Positivo",
  "probabilidad": 0.92
}
```

---

### 😠 Ejemplo 2: Comentario Negativo
```bash
curl -X POST http://localhost:8080/sentiment \
  -H "Content-Type: application/json" \
  -d '{"text": "Pésimo servicio, nunca más compro aquí"}'
```

**Resultado esperado:**
```json
{
  "sentimiento": "Negativo",
  "probabilidad": 0.88
}
```

---

### 😐 Ejemplo 3: Comentario Neutral
```bash
curl -X POST http://localhost:8080/sentiment \
  -H "Content-Type: application/json" \
  -d '{"text": "El pedido llegó el martes como se indicó"}'
```

**Resultado esperado:**
```json
{
  "sentimiento": "Neutro",
  "probabilidad": 0.75
}
```

---

## 🔍 ¿Cómo veo los logs? (Para debugging)

Los logs te ayudan a entender qué está pasando "detrás de cámaras". Están configurados en:
```
src/main/resources/application.properties
```

### Configuración básica:
```properties
# Nivel general de logs
logging.level.root=INFO

# Logs más detallados de nuestra app
logging.level.com.sentiment=DEBUG

# Formato bonito para leer
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

**¿Qué significa esto?**
- 🟢 **INFO**: Información general (todo OK)
- 🟡 **DEBUG**: Información detallada (para investigar)
- 🔴 **ERROR**: Algo salió mal

---

## 🤝 ¿Cómo funciona la integración con Data Science?

Es más simple de lo que parece:

1. **Tú envías** → El Backend recibe tu texto
2. **Backend valida** → "¿El texto tiene contenido? ✅"
3. **Backend pregunta** → Envía el texto al módulo de Data Science
4. **Data Science responde** → "Es Positivo con 90% de confianza"
5. **Backend te responde** → Te devuelve el resultado en formato JSON

### 🧠 ¿Qué hace Data Science?
Usa técnicas de Machine Learning:
- **TF-IDF**: Identifica palabras importantes ("excelente", "pésimo", etc.)
- **Regresión Logística**: Aprende a clasificar sentimientos basándose en patrones

---

## 📁 Estructura del proyecto

```
Backend/
├── 📂 src/
│   ├── 📂 main/
│   │   ├── ☕ java/          → Código de la aplicación
│   │   └── ⚙️ resources/     → Configuraciones
│   └── 🧪 test/              → Pruebas automatizadas
├── 🐳 Dockerfile             → Receta para crear el contenedor
├── 🎼 docker-compose.yml     → Orquestador de servicios
├── 📦 pom.xml                → Dependencias del proyecto
└── 📖 README.md              → ¡Este archivo!
```

---

## 🚨 ¿Qué pasa si algo sale mal?

El sistema está preparado para manejar errores:

| Código | ¿Qué significa? | Solución |
|--------|-----------------|----------|
| 400 | ❌ El texto está vacío | Envía un texto válido |
| 500 | 🔥 Error interno | Revisa los logs |
| 503 | 💤 Data Science no responde | Verifica que el servicio DS esté activo |

---

## ✅ Checklist para verificar que todo funciona

- [ ] Docker está instalado y corriendo
- [ ] El contenedor está activo (`docker ps`)
- [ ] Puedes acceder a http://localhost:8080
- [ ] El endpoint `/sentiment` responde correctamente
- [ ] Los logs se ven sin errores

---

## 🌈 Ideas para el futuro

Cosas que nos gustaría agregar:

- 📊 Endpoint `/stats` para ver estadísticas generales
- 🌍 Soporte para múltiples idiomas (Español + Portugués)
- 🔍 Explicar qué palabras influyeron en la predicción
- 📤 Subir archivos CSV con muchos comentarios a la vez
- 🎨 Una interfaz web mejorada para probar fácilmente

---

## 👥 Sobre el equipo

Este proyecto fue creado con ❤️ por el **Equipo 25 - Grupo 8** durante el Hackathon de **No Country (ONE II – Latam)**.

Somos estudiantes aprendiendo a integrar Backend con Data Science, y este es nuestro primer MVP funcional. ¡Estamos orgullosos de lo que logramos! 🎉

---

## 🆘 ¿Necesitas ayuda?

Si tienes problemas:

1. 🔍 Revisa los logs: `docker logs <nombre-contenedor>`
2. 📖 Consulta la documentación de Swagger
3. 🐛 Verifica que todos los servicios estén corriendo
4. 💬 Abre un issue en el repositorio

---

## 📝 Notas finales

- 🎓 Este es un proyecto educativo creado durante un hackathon
- 🏗️ El código sigue buenas prácticas para ser fácil de mantener
- 🚀 Es un MVP (Producto Mínimo Viable) funcional y escalable
- 🤓 ¡Ideal para aprender sobre integración de microservicios!

---

## 🎉 ¡Gracias por explorar SentimentAPI!

Si te gustó el proyecto o tienes sugerencias, ¡no dudes en contribuir! 🌟

Happy coding! 💻✨

