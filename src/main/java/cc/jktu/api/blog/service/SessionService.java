package cc.jktu.api.blog.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class SessionService {

    private final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();

    public void add(String token, Integer userId) {
        cache.put(token, userId);
    }

    public void add(String token, Integer userId, Long timeout) {
        add(token, userId);
    }

    public Integer get(String token) {
        return cache.get(token);
    }

    public void remove(String token) {
        cache.remove(token);
    }

}
