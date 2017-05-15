package com.Ecommerce.repositories;

import com.Ecommerce.configuration.RepositoryConfig;
import com.Ecommerce.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Ovi on 5/15/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfig.class})
public class UserRepositoryTest {


    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Test
    public void userTest(){

        User user = new User();
        user.setUsername("Andrei");
        user.setPassword("password");

        assertNull(user.getId());
        userRepository.save(user);
        assertNotNull(user.getId());

        User fetchedUser = userRepository.findOne(user.getId());
        assertNotNull(fetchedUser);

        assertEquals(user.getId(),fetchedUser.getId());
        assertEquals(user.getUsername(),fetchedUser.getUsername());

        fetchedUser.setUsername("AndreiUp");
        userRepository.save(fetchedUser);

        User updateUser = userRepository.findOne(fetchedUser.getId());
        assertEquals(fetchedUser.getUsername(),updateUser.getUsername());

        long userCount = userRepository.count();
        assertEquals(userCount,1);
        Iterable<User> users = userRepository.findAll();

        int count = 0;

        for (User u: users){
            count++;
        }

        assertEquals(count,1);

        userRepository.delete(updateUser.getId());
        assertEquals(0,userRepository.count());
    }
}
