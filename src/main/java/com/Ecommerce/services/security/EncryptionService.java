package com.Ecommerce.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface EncryptionService {

    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
    void setStrongPasswordEncryptor(StrongPasswordEncryptor strongEncryptor);
}
