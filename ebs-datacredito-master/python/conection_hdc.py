from zeep import Client
from zeep.wsse.signature import Signature
from zeep.transports import Transport
from requests import Session
from zeep import xsd, helpers
from zeep.wsse.username import UsernameToken
import xmltodict
import json
import hug


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


def getCreditHistory(
        identificacion,
        primerApellido,
        tipoIdentificacion,
        urlDatacredito,
        usuarioOkta,
        claveOkta,
        usuario,
        claveCorta):

    print("Creating session")
    session = Session()
    # Parametros Conexion
    # Keystore en Formato PEM
    session.cert = './coopechance.pem'
    # Credenciales OKTA
    # Okta_User = UsernameToken('2-891502277', 'oktaEBS1')
    print("Setting OKTA user data")
    Okta_User = UsernameToken(usuarioOkta, claveOkta)
    # Private Key SSL
    private_key_filename = './private.key'
    # SSL Certificate
    public_key_filename = './certificate.crt'

    print("Generating signature")
    signature = Signature(private_key_filename, public_key_filename)

    print("Start request to Datacredito")
    transport = Transport(session=session)
    # URL = 'https://demo-servicesesb.datacredito.com.co:443/wss/dhws3/services/DHServicePlus?wsdl'
    URL = urlDatacredito
    client = Client(URL, wsse=CustomSignature(
        [Okta_User, signature]), transport=transport)
    service = client.bind('ServicioHistoriaCreditoPlus',
                          'ServicioHistoriaCreditoPlus.dmz.https')

    #    request_data = {
    #        'clave': '58FPE',
    #        'identificacion': identificacion,
    #        'primerApellido': primerApellido,
    #        'producto': '64',
    #        'tipoIdentificacion': tipoIdentificacion,
    #        'usuario': '891502277',
    #    }

    request_data = {
        'clave': claveCorta,
        'identificacion': identificacion,
        'primerApellido': primerApellido,
        'producto': '64',
        'tipoIdentificacion': tipoIdentificacion,
        'usuario': usuario,
    }

    print("Process request to Datacredito")
    response_service = client.service.consultarHC2(solicitud=request_data)
    print("Connection OK!")
    response_service = response_service.replace('&lt;', '<')
    response_service = response_service.replace('&gt;', '>')
    response_service = response_service.replace('&amp;', '&')
    response_service = response_service.replace('&nbsp;', ' ')
    response_service = response_service.replace('&#64;', '@')
    response_service = response_service.replace('&quot;', '"')
    response_service = response_service.replace('&apos;', "'")
    response_service = helpers.serialize_object(response_service)
    response_service = xmltodict.parse(response_service)
    response_service = json.dumps(response_service, ensure_ascii=False)
    response_service = response_service.replace('@', '')
    print("Response OK!")
    return json.loads(response_service);


@hug.get('/datacredito/api/v1/hdc',
         examples='identificacion=21228392&primerApellido=PORRAS&tipoIdentificacion=1')
@hug.local()
def hdc(identificacion: hug.types.text,
        primerApellido: hug.types.text,
        tipoIdentificacion: hug.types.text,
        urlDatacredito: hug.types.text,
        usuarioOkta: hug.types.text,
        claveOkta: hug.types.text,
        usuario: hug.types.text,
        claveCorta: hug.types.text):
    print("Getting data from java applcation")
    return getCreditHistory(identificacion=identificacion,
                            primerApellido=primerApellido,
                            tipoIdentificacion=tipoIdentificacion,
                            urlDatacredito=urlDatacredito,
                            usuarioOkta=usuarioOkta,
                            claveOkta=claveOkta,
                            usuario=usuario,
                            claveCorta=claveCorta)
