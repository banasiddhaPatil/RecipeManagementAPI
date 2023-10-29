package com.geekster.RecipeManagementSystemAPI.Services.utility;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordEncrypter {
    public static String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        return DatatypeConverter.printHexBinary(digested);
    }
}
