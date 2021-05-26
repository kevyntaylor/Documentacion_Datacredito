package com.ebs.datacredito.controller;

import com.ebs.datacredito.model.Log;
import com.ebs.datacredito.service.interfaces.ILogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@RestController
@RequestMapping("datacredito/api/v1/ping")
@Api(value = "ping", tags = "Ping", description = "Test connection to service")
public class PingController {

    private final ILogService logService;

    @Autowired
    public PingController(ILogService logService) {
        this.logService = logService;
    }

    @GetMapping
    @ApiOperation(value = "Test connection")
    public @ResponseBody
    String ping(HttpServletRequest request) {
        logService.create(new Log(String.format("Test connection valid"), request.getRemoteAddr(), new Timestamp(System.currentTimeMillis())));
        return "pong!";
    }

}
