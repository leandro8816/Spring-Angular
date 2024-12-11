package com.event.demo.auth;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TokenStorage {
    private static final ConcurrentHashMap<String, String> activeTokens = new ConcurrentHashMap<>();

    public static void addToken(String token, String username) {
        activeTokens.put(token, username);
    }

    public static void removeToken(String token) {
        activeTokens.remove(token);
    }

    public static Set<String> getActiveUsers() {
        return new HashSet<>(activeTokens.values());
    }
}