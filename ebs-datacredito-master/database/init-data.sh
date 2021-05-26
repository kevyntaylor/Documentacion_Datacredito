#!/bin/bash

echo Creando objetos de base de datos

DB=$(grep POSTGRES_DB ./database/database.env | cut -d '=' -f2)
BD_USER=$(grep POSTGRES_USER ./database/database.env | cut -d '=' -f2)
BD_PASSWORD=$(grep POSTGRES_PASSWORD ./database/database.env | cut -d '=' -f2)
echo Working in database $DB with user $BD_USER and password $BD_PASSWORD

echo "START TO TRANSFER SCRIPTS"
docker cp ./database/Scripts/0-DB_Init-Schema.sql postgres_container_datacredito:/home/0-DB_Init-Schema.sql
docker cp ./database/Scripts/1-DB_Init-Sequences.sql postgres_container_datacredito:/home/1-DB_Init-Sequences.sql
docker cp ./database/Scripts/2-DB_Init-Tables.sql postgres_container_datacredito:/home/2-DB_Init-Tables.sql
docker cp ./database/Scripts/3-DB_Init-Procedures.sql postgres_container_datacredito:/home/3-DB_Init-Procedures.sql
docker cp ./database/Scripts/4-DB_Init-Triggers.sql postgres_container_datacredito:/home/4-DB_Init-Triggers.sql
docker cp ./database/Scripts/5-DB_Init-Views.sql postgres_container_datacredito:/home/5-DB_Init-Views.sql
docker cp ./database/Scripts/6-DB_Init-Data.sql postgres_container_datacredito:/home/6-DB_Init-Data.sql
echo "END TO TRANSFER SCRIPTS"

echo "START TO EXECUTE SCRIPTS"
docker exec --tty -it postgres_container_datacredito psql -U $BD_USER -d $DB -f /home/0-DB_Init-Schema.sql
docker exec --tty -it postgres_container_datacredito psql -U $BD_USER -d $DB -f /home/1-DB_Init-Sequences.sql
docker exec --tty -it postgres_container_datacredito psql -U $BD_USER -d $DB -f /home/2-DB_Init-Tables.sql
docker exec --tty -it postgres_container_datacredito psql -U $BD_USER -d $DB -f /home/3-DB_Init-Procedures.sql
docker exec --tty -it postgres_container_datacredito psql -U $BD_USER -d $DB -f /home/4-DB_Init-Triggers.sql
docker exec --tty -it postgres_container_datacredito psql -U $BD_USER -d $DB -f /home/5-DB_Init-Views.sql
docker exec --tty -it postgres_container_datacredito psql -U $BD_USER -d $DB -f /home/6-DB_Init-Data.sql
echo "END TO EXECUTE SCRIPTS"