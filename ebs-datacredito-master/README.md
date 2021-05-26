# Datacredito API

## Compilation

1. Compile java project with maven. You can use an IDE or execute mvn command in a terminal
   
   ```bash
   mvn clean compile package
   ```

2. Create zip for production installation executing the following command

   ```bash
   sh create-zip-for-windows-server.sh
   ```
   If you work in windows, do:

2.1. Create a folder named _api-ebs-datacredito_

2.2. Copy the following files and folders inside the folder _api-ebs-datacredito_

./python
./database
./target
./Dockerfile
./docker-compose.yml
./install-docker.bat
./install-docker.sh
./start-existing-docker.sh
./start-existing-docker.bat

2.3. Compress folder _api-ebs-datacredito_

2.4. Send for installation _api-ebs-datacredito.zip_ file.


## Instalation

### Install with docker 

1. Decompress _api-ebs-datacredito.zip_ file.

2. Enter the folder _api-ebs-datacredito_.

3. Execute install-docker.sh

   ```bash
   sh install-docker.sh
   ```

### Install monolithic mode

1. Decompress _api-ebs-datacredito.zip_ file.
   
2. Install Python with de following dependencies

    2.1. Install [pip](https://pip.pypa.io/en/stable/installing/) package administrator
    
    2.2. Install [zeep](https://docs.python-zeep.org/en/master/) library
   ```bash
   pip install zeep
   ```

   2.3. Install [xmltodict](https://pypi.org/project/xmltodict/) library
   ```bash
   pip install xmltodict
   ```

   2.4. Install [xmlsec](https://pypi.org/project/xmlsec/) library
   ```bash
   pip install xmlsec
   ```
   If previous step triggered an error
   
      2.4.1. Install [brew](https://brew.sh) package administrator   

      2.4.2. Re try install [xmlsec](https://pypi.org/project/xmlsec/) library
   
      ``` bash
      brew install Libxmlsec1
      brew install pkg-config
      pip install xmlsec
      pip install hug
      ```
   
   2.5. Execute python/conection_hdc.py file with the following command:

   ```bash
   hug -f conection_hdc.py -p 8091
   ```
   
   This python process should be run as a service in the server. 
   
3. Install PostgreSQL

   3.1. Execute all files .sql located in database/Scripts folder.

4. Install JDK 16.

5. Execute target/datacredito-1.0.1.jar file with the following command:

   ```bash
   java -jar -Dspring.profiles.active=prod datacredito-1.0.1.jar
   ```

   This java process should be run as a service in the server.

   _If you prefer, you can compile project in **war** file for a tomcat application server_

## Maintenance

### Docker 

1. For access to containers you can run in terminal

```bash
docker logs <container-name>
```

## Services exposed 

Python API: http://localhost:8091

Java API: http://localhost:8080

Documentation EBS API: http://localhost:8080/swagger-ui.html