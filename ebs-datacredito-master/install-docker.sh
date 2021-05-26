#!/bin/bash

# [DELETING CONTAINERS]
echo Eliminando contenedores e imagenes previamente instaladas

# [Database]
docker stop postgres_container_datacredito
docker rm postgres_container_datacredito
docker rmi postgres_image_datacredito
echo Eliminando carpeta de almacenamiento de datos
# Change to path for production
sudo rm -rf /tmp/docker/demo/data

# [Python client]
docker stop python_client_container
docker rm python_client_container
docker rmi python_client_image

# [Java API]
docker stop java_api_container
docker rm java_api_container
docker rmi java_api_image


# [CREATING CONTAINERS]
echo Creando imagenes y contenedores

# [Database]
echo Creando carpeta de almacenamiento de datos
mkdir -p /tmp/docker/demo/data

echo Creando imagen de base de datos
docker build --target db -t postgres_image_datacredito .

# [Python client]
echo Creando imagen de cliente python
docker build --target python-client -t python_client_image .

# [Java API]
echo Creando imagen de cliente python
docker build --target java-api -t java_api_image .

echo Iniciando contenedor con docker compose
docker-compose up -d
sleep 20

echo Creando objetos de base de datos
sh ./database/init-data.sh