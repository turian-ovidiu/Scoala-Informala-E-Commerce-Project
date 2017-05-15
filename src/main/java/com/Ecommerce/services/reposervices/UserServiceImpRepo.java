package com.Ecommerce.services.reposervices;

import com.Ecommerce.domain.CartDetail;
import com.Ecommerce.domain.User;
import com.Ecommerce.repositories.UserRepository;
import com.Ecommerce.services.UserService;
import com.Ecommerce.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ovi on 5/14/2017.
 */

@Service
@Transactional(readOnly = true)
public class UserServiceImpRepo implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }


    @Override
    @RolesAllowed("ADMIN")
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public User saveOrUpdate(User domainObject) {
        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));

        }
        return userRepository.save(domainObject);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Integer countCartProducts(User user) {
        Integer tempInt = 0;
        for (CartDetail cartDetail:user.getCart().getCartDetails()){
            tempInt++;
        }
        return tempInt;
    }
}
