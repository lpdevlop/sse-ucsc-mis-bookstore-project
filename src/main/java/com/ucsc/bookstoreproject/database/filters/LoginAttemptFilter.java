package com.ucsc.bookstoreproject.database.filters;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginAttemptFilter {

    private static final int MAX_ATTEMPTS = 5;
    private static final long LOCK_TIME_MS = 15L * 60L * 1000L;

    private final Map<String, AttemptInfo> ipAttempts = new ConcurrentHashMap<>();

    public void loginSucceeded(String ip) {
        ipAttempts.remove(ip);
    }

    public void loginFailed(String ip) {
        AttemptInfo info = ipAttempts.computeIfAbsent(ip, k -> new AttemptInfo());
        info.attempts++;
        info.lastAttempt = System.currentTimeMillis();
    }

    public boolean isBlocked(String ip) {
        AttemptInfo info = ipAttempts.get(ip);
        if (info == null) return false;

        if (info.attempts >= MAX_ATTEMPTS) {
            long elapsed = System.currentTimeMillis() - info.lastAttempt;
            if (elapsed < LOCK_TIME_MS) {
                return true;
            } else {
                ipAttempts.remove(ip);
            }
        }
        return false;
    }

    private static class AttemptInfo {
        int attempts = 0;
        long lastAttempt;
    }

    public String getClientIP(HttpServletRequest request) {
        String xf = request.getHeader("X-Forwarded-For");
        return (xf != null) ? xf.split(",")[0] : request.getRemoteAddr();
    }
}
