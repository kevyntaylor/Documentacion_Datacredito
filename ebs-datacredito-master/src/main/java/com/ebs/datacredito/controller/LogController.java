package com.ebs.datacredito.controller;

import com.ebs.datacredito.model.Log;
import com.ebs.datacredito.model.User;
import com.ebs.datacredito.service.interfaces.ILogService;
import com.ebs.datacredito.service.interfaces.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(path = "datacredito/api/v1/log")
@Api(value = "logs", tags = "Logs", description = "Log query")
public class LogController {

    private final ILogService logService;

    @Autowired
    public LogController(ILogService logService) {
        this.logService = logService;
    }

    @GetMapping(path = "/page/{page}")
    @ApiOperation(value = "Get paginated logs", authorizations = {@Authorization(value = "jwtToken")})
    public @ResponseBody
    Page<Log> get(HttpServletRequest request, @PathVariable("page") int page) {
        logService.create(new Log(String.format("Get all logs"), request.getRemoteAddr(), new Timestamp(System.currentTimeMillis())));
        return logService.get(PageRequest.of(page, 50));
    }


}
