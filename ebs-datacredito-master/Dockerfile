FROM postgres:12 as db

FROM python:3 as python-client
RUN mkdir -p /ebs-datacredito
COPY python/conection_hdc.py /
COPY python/coopechance.pem /
COPY python/certificate.crt /
COPY python/private.key /
RUN python -m pip install --upgrade pip
RUN pip install --upgrade pip setuptools wheel
RUN pip install xmltodict
RUN pip install zeep
RUN apt-get update && \
    apt-get install -y \
    libxml2-dev libxmlsec1-dev libxmlsec1-openssl
RUN pip install zeep[xmlsec]
RUN pip install hug
CMD [ "hug", "-f", "conection_hdc.py", "-p", "8091"]

FROM openjdk:16-jdk-alpine as app
WORKDIR /usr/src/javaapidatacredito
COPY target/datacredito-1.0.1.jar /usr/src/javaapidatacredito
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "datacredito-1.0.1.jar"]