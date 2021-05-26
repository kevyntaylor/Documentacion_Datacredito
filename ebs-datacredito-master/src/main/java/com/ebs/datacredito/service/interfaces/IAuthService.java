package com.ebs.datacredito.service.interfaces;

public interface IAuthService {

    boolean isValidUser(String email, String password);
    String getJWTToken(String username);

}
