package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@ContextConfiguration(classes = RedisConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testGetUserById() throws InterruptedException {
        String id = "1";
        List<User> users = userService.getAll();
        List<User> users2 = userService.getAll();
        Cache usersCache = cacheManager.getCache("users");
        assertNotNull(usersCache);
    }
}
