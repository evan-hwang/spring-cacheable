package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Caching(
            evict = @CacheEvict(value = "user", key = "'all'"),
            put = @CachePut(value = "user", key = "#result.id")
    )
    public User create(User user) {
        LOGGER.info("캐시에서 유저 ID {}을(를) 생성합니다.", user.getId());
        return userRepository.save(user);
    }

    @Cacheable(value = "user", key = "'all'")
    public List<User> getAll() {
        LOGGER.info("캐시에서 유저 목록을 조회합니다.");
        return userRepository.findAll();
    }

    @Cacheable(value = "user", key = "#id")
    public User getById(Long id) {
        LOGGER.info("캐시에서 유저 ID {}을(를) 조회합니다.", id);
        return userRepository.findById(id);
    }

    @CacheEvict(value = "user", key = "'all'")
    @CachePut(value = "user", key = "#user.id")
    public User update(User user) {
        LOGGER.info("캐시에서 유저 ID {}을(를) 업데이트합니다.", user.getId());
        return userRepository.save(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "user", key = "'all'"),
            @CacheEvict(value = "user", key = "#id")
    })
    public void deleteById(Long id) {
        LOGGER.info("캐시에서 유저 ID {}을(를) 삭제합니다.", id);
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "user")
    public void deleteAll() {
        LOGGER.info("캐시에서 모든 유저 목록을 삭제합니다.");
        userRepository.deleteAll();
    }
}
