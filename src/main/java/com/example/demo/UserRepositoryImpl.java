package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    // 인메모리 DB 역할을 할 HashMap
    private Map<Long, User> users = new HashMap<>();
    private long nextId = 1L;

    @Override
    public List<User> findAll() {
        LOGGER.info("DB에서 유저 목록을 조회합니다...");
        // DB 쿼리문 대신 Sleep 코드 추가
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 모든 유저 정보를 리스트에 담아 반환
        return new ArrayList<>(users.values());
    }

    @Override
    public User findById(Long id) {
        LOGGER.info("DB에서 ID가 {}인 유저를 조회합니다...", id);
        // DB 쿼리문 대신 Sleep 코드 추가
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // HashMap에서 해당 ID의 유저 정보를 가져옴
        return users.get(id);
    }

    @Override
    public User save(User user) {
        LOGGER.info("DB에 유저 정보를 저장합니다...");

        // 새로운 유저인 경우 ID를 부여하고 HashMap에 저장
        if (user.getId() == null) {
            user.setId(nextId++);
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("DB에서 ID가 {}인 유저 정보를 삭제합니다...", id);

        // HashMap에서 해당 ID의 유저 정보를 삭제
        users.remove(id);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("DB에서 유저 목록을 삭제합니다...");

        // 모든 유저 정보를 삭제
        users.clear();
    }

}

