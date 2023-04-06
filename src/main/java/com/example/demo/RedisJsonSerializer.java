package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RedisJsonSerializer<T> implements RedisSerializer<T>, KeyGenerator {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(T t) throws SerializationException {
        try {
            return objectMapper.writeValueAsString(t).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Failed to serialize object", e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        try {
            return objectMapper.readValue(bytes, (Class<T>) Object.class);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Failed to deserialize object", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object generate(Object o, java.lang.reflect.Method method, Object... objects) {
        return new SimpleKey(method.getName(), objects);
    }
}


