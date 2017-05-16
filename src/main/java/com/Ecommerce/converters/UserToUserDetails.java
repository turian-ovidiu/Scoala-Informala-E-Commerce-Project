package com.Ecommerce.converters;

import com.Ecommerce.domain.User;

import com.Ecommerce.services.security.UserDetailsImp;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Turian Ovidiu.
 * This class represent the implementation of Converter interface.
 * This class will convert a User to UserDetails.
 */
@Component
public class UserToUserDetails implements Converter<User,UserDetails>{

    @Override
    public UserDetails convert(User user) {
        UserDetailsImp userDetails = new UserDetailsImp();

        if (user != null){
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getEncryptedPassword());
            userDetails.setEnabled(user.getEnabled());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            });
            userDetails.setAuthorities(authorities);

        }

        return userDetails;
    }
}
