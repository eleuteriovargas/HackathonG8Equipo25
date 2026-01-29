# Etapa de ejecución
FROM nginx:alpine

# Autor del proyecto
LABEL maintainer="Melvin <melvingutierremb@gmail.com>"

# Borramos los archivos por defecto de Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copiamos los archivos locales al contenedor (RUTA ORIGEN -> RUTA DESTINO)
COPY . /usr/share/nginx/html

# Exponemos el puerto 80
EXPOSE 80

# Iniciamos nginx
CMD ["nginx", "-g", "daemon off;"]