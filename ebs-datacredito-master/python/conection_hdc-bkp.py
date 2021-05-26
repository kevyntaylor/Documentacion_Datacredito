from zeep import Client
from zeep.wsse.signature import Signature
from zeep.transports import Transport
from requests import Session
from requests.auth import HTTPBasicAuth
from zeep import xsd, helpers
from zeep.wsse.username import UsernameToken
import xmltodict
import json


class CustomSignature(object):
    """Sign given SOAP envelope with WSSE sig using given key and cert."""
    """Clase Custom"""

    def __init__(self, wsse_list):
        self.wsse_list = wsse_list

    def apply(self, envelope, headers):
        for wsse in self.wsse_list:
            envelope, headers = wsse.apply(envelope, headers)
        return envelope, headers

    def verify(self, envelope):
        pass


session = Session()
# Parametros Conexion
# Keystore en Formato PEM
session.cert = './coopechance.pem'
# Credenciales OKTA
Okta_User = UsernameToken('2-891502277', 'oktaEBS1')
# Private Key SSL
private_key_filename = './private.key'
# SSL Certificate
public_key_filename = './certificate.crt'
signature = Signature(private_key_filename, public_key_filename)
transport = Transport(session=session)
URL = 'https://demo-servicesesb.datacredito.com.co:443/wss/dhws3/services/DHServicePlus?wsdl'
client = Client(URL, wsse=CustomSignature(
    [Okta_User, signature]), transport=transport)
service = client.bind('ServicioHistoriaCreditoPlus',
                      'ServicioHistoriaCreditoPlus.dmz.https')

request_data = {
    'clave': '58FPE',
    'identificacion': '21228392',
    'primerApellido': 'PORRAS',
    'producto': '64',
    'tipoIdentificacion': '1',
    'usuario': '891502277',
}

response_service = client.service.consultarHC2(solicitud=request_data)
print("Conexion OK \n", response_service)
response_service = response_service.replace('&lt;', '<')
response_service = helpers.serialize_object(response_service)
response_service = xmltodict.parse(response_service)
response_service = json.dumps(response_service, ensure_ascii=False)
response_service = response_service.replace('@', '')
print("Conexion OK \n", response_service)
