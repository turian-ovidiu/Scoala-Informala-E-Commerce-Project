package com.Ecommerce.services.security;

/**
 * Created by Ovi on 5/14/2017.
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);
}
