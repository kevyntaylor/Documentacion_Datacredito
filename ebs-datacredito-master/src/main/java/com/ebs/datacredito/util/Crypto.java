package com.ebs.datacredito.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class Crypto {

    public static String encrypt(String toEncrypt) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoded = bCryptPasswordEncoder.encode(toEncrypt);
        return encoded;
    }

    public static boolean matchEncrypt(String decrypted, String encrypted) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(decrypted, encrypted);
    }

}
