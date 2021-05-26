package com.ebs.datacredito.controller;

import com.ebs.datacredito.model.Log;
import com.ebs.datacredito.service.interfaces.IDatacreditoInfoService;
import com.ebs.datacredito.service.interfaces.ILogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;

@RestController
@RequestMapping(path = "datacredito/api/v1/info")
@Api(value = "datacredito", tags = "Datacredito", description = "Get info from Datacredito")
public class DatacreditoInfoController {

    private final IDatacreditoInfoService datacreditoInfo;
    private final ILogService logService;

    @Autowired
    public DatacreditoInfoController(IDatacreditoInfoService datacreditoInfo,
                                     ILogService logService) {
        this.datacreditoInfo = datacreditoInfo;
        this.logService = logService;
    }

    @GetMapping
    @ApiOperation(value = "Get customer info from Datacredito", authorizations = {@Authorization(value = "jwtToken")})
    public @ResponseBody
    String get(
            HttpServletRequest request,
            @RequestParam(required = true) String identification,
            @RequestParam(required = true) String primerApellido,
            @RequestParam(required = true) String tipoIdentificacion
    ) throws Exception {

        try {
            String response = datacreditoInfo.getDatacreditoInfo(identification, primerApellido, tipoIdentificacion);
            logService.create(new Log(
                    String.format("Get data from Datacredito for identification=[%s] firstName=[%s] identificationType=[%s]",
                            identification, primerApellido, tipoIdentificacion),
                    request.getRemoteAddr(),
                    new Timestamp(System.currentTimeMillis())));
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            logService.create(new Log(
                    String.format("ERROR getting data from Datacredito for identification=[%s] firstName=[%s] identificationType=[%s]",
                            identification, primerApellido, tipoIdentificacion),
                    request.getRemoteAddr(),
                    new Timestamp(System.currentTimeMillis())));
            throw new Exception("Error getting data");
        }
    }

}
