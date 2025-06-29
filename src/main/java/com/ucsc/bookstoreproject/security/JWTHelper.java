package com.ucsc.bookstoreproject.security;


import com.ucsc.bookstoreproject.database.model.UserModel;
import com.ucsc.bookstoreproject.exceptions.CustomException;
import com.ucsc.bookstoreproject.utils.KeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

@Component
@Slf4j
public class JWTHelper {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;



    @PostConstruct
    public  void initKeys() throws  Exception{

        this.publicKey = KeyUtils.loadPublicKey("public_key.pem");
        this.privateKey = KeyUtils.loadPrivateKey("private_key.pem");
        log.info("RSA keys loaded successfully");

    }


    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public String generateToken(UUID uuid, List<String> role) {

        return Jwts.builder()
                .setSubject(uuid.toString())
                .claim("authorities", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10 minutes
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    public boolean validateToken(String token, UserModel userDetails) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getPublicKey())
                    .requireSubject(userDetails.getUuid().toString())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extractEmail(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getPublicKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("email", String.class);
        } catch (Exception ex) {
            throw new CustomException("Invalid token", HttpStatus.BAD_REQUEST);
        }
    }

}
