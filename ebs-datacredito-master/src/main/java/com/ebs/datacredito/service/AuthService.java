package com.ebs.datacredito.service;

import com.ebs.datacredito.model.User;
import com.ebs.datacredito.repository.IUserRepository;
import com.ebs.datacredito.service.interfaces.IAuthService;
import com.ebs.datacredito.util.Crypto;
import com.ebs.datacredito.util.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;

    @Autowired
    public AuthService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValidUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(!userOptional.isPresent()) {
            return false;
        }
        User user = userOptional.get();
        return user.isActive() && Crypto.matchEncrypt(password, user.getPassword());
    }

    @Override
    public String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("ebsdatacredito")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,
                        SecurityConstants.SECRET_KEY.getBytes()).compact();

        return token;
    }

}
