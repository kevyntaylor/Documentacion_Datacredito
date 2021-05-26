package com.ebs.datacredito.controller;

import com.ebs.datacredito.model.Log;
import com.ebs.datacredito.service.AuthService;
import com.ebs.datacredito.service.interfaces.IAuthService;
import com.ebs.datacredito.service.interfaces.ILogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Slf4j
@RestController
@RequestMapping(path = "datacredito/api/v1/auth")
@Api(value="auth", tags = "Auth", description = "Authorize users")
public class AuthController {

    private final IAuthService authService;
    private final ILogService logService;

    @Autowired
    public AuthController(AuthService authService, ILogService logService) {
        this.authService = authService;
        this.logService = logService;
    }

    @PostMapping
    @ApiOperation(value = "Authorize users")
    public @ResponseBody
    ResponseEntity login(
            HttpServletRequest request,
            @RequestParam("email") String email,
            @RequestParam("password") String password) throws Exception {

        log.info("Login with user [{}]", email, password);
        if(authService.isValidUser(email, password)){
            String token = authService.getJWTToken(email);
            logService.create(new Log(String.format("Login valid for user [%s]", email), request.getRemoteAddr(), new Timestamp(System.currentTimeMillis())));
            return ResponseEntity.ok(token);
        } else {
            logService.create(new Log(String.format("Login invalid for user [%s]", email), request.getRemoteAddr(), new Timestamp(System.currentTimeMillis())));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
