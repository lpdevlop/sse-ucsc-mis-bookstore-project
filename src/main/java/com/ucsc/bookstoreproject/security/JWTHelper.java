package com.ucsc.bookstoreproject.security;


import com.ucsc.bookstoreproject.database.model.UserModel;
import com.ucsc.bookstoreproject.utils.keyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

@Component
public class JWTHelper {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;



    @PostConstruct
    public  void initKeys() throws  Exception{

        this.publicKey = keyUtils.loadPublicKey("public_key.pem");
        this.privateKey = keyUtils.loadPrivateKey("private_key.pem");
        System.out.println("🔐 RSA keys loaded successfully");

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
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_00)) // 1 day
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
            throw new RuntimeException("Invalid token", ex);
        }
    }

}
