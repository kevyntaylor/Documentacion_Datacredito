#!/bin/bash

rm -rf ./api-ebs-datacredito.zip
rm -rf ./api-ebs-datacredito
mkdir ./api-ebs-datacredito

cp -r ./python ./api-ebs-datacredito
cp -r ./database ./api-ebs-datacredito
cp -r ./target ./api-ebs-datacredito
cp -r ./Dockerfile ./api-ebs-datacredito
cp -r ./docker-compose.yml ./api-ebs-datacredito
cp -r ./install-docker.bat ./api-ebs-datacredito
cp -r ./install-docker.sh ./api-ebs-datacredito
cp -r ./start-existing-docker.sh ./api-ebs-datacredito
cp -r ./start-existing-docker.bat ./api-ebs-datacredito

zip -r api-ebs-datacredito.zip ./api-ebs-datacredito

rm -rf ./api-ebs-datacredito
