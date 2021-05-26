package com.ebs.datacredito.service.interfaces;

import java.io.IOException;

public interface IDatacreditoInfoService {
    public String getDatacreditoInfo(String identifiction,
                                     String primerApellido,
                                     String tipoIdentificacion) throws IOException;
}
