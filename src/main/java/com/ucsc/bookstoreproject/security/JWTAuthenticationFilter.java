package com.ucsc.bookstoreproject.security;

import com.ucsc.bookstoreproject.database.model.UserModel;
import com.ucsc.bookstoreproject.utils.Constant;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Slf4j
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    private final JWTHelper jwtHelper;


    private final CustomUserDetailsService customUserDetailsService;



    public JWTAuthenticationFilter(JWTHelper jwtHelper, CustomUserDetailsService customUserDetailsService) {
        this.jwtHelper = jwtHelper;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(Constant.AUTHORIZATION);

        log.info(" Header :  {}", authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith(Constant.BEARER)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied: Missing or invalid Authorization header");
            return;
        }
        final String token = authorizationHeader.substring(7);

        try {

            String username = this.jwtHelper.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserModel userDetails = customUserDetailsService.loadUserByUuid(username);

                boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
                if (validateToken) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.info("JWT token validation failed for user: {}", username);
                }
            }
        }
        catch (IllegalArgumentException | MalformedJwtException e) {
            log.info("Invalid JWT token: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied: Invalid JWT toke"+e.getMessage());
        } catch (ExpiredJwtException e ) {
            log.info("JWT token expired: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied: JWT token expired"+e.getMessage());
        }catch (Exception e) {
            log.error("Error processing JWT token", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied: Error processing JWT token"+ e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return  path.startsWith("/api/v1/auth/login") ||
                path.equals("/images/") ||
                path.equals("/api/v1/book/latest") ||
                path.equals("/api/v1/book/top") ||
                path.equals("/api/v1/user/create") ||
                path.equals("/api/v1/book/search") ||
                path.equals("/api/v1/book/recommendations") ||
                path.startsWith("/api/v1/book/isbn");
    }
}