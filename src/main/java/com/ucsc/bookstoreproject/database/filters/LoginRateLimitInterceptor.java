package com.ucsc.bookstoreproject.database.filters;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginRateLimitInterceptor implements HandlerInterceptor {

    private final Map<String, Bucket> ipBucketMap = new ConcurrentHashMap<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().equals("/api/v1/auth/login") && request.getMethod().equalsIgnoreCase("POST")) {
            String ip = getClientIP(request);
            Bucket bucket = ipBucketMap.computeIfAbsent(ip, k -> newBucket());

            if (bucket.tryConsume(1)) {
                return true;
            } else {
                response.setStatus(429);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Too many  requests. Please wait a minute.\"}");
                return false;
            }
        }
        return true;
    }

    private Bucket newBucket() {
        Refill refill = Refill.intervally(3, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(3, refill);
        return Bucket.builder().addLimit(limit).build();
    }

    private String getClientIP(HttpServletRequest request) {
        String xf = request.getHeader("X-Forwarded-For");
        return (xf != null) ? xf.split(",")[0] : request.getRemoteAddr();
    }

}
