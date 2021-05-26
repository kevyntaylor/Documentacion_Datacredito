REM [DELETING CONTAINERS]
echo Eliminando contenedores e imagenes previamente instaladas

REM [Database]
docker stop postgres_container_datacredito
docker rm postgres_container_datacredito
docker rmi postgres_image_datacredito

REM [Python client]
docker stop python_client_container
docker rm python_client_container
docker rmi python_client_image

REM [Java API]
docker stop java_api_container
docker rm java_api_container
docker rmi java_api_image


REM [CREATING CONTAINERS]
echo Creando imagenes y contenedores

echo Creando imagen de base de datos
docker build --target db -t postgres_image_datacredito .

REM [Python client]
echo Creando imagen de cliente python
docker build --target python-client -t python_client_image .

REM [Java API]
echo Creando imagen de cliente python
docker build --target java-api -t java_api_image .

echo Iniciando contenedor con docker compose
docker-compose up -d
timeout /t 15