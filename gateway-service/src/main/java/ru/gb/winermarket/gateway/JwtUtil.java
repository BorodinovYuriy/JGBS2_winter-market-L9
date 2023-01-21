package ru.gb.winermarket.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Slf4j
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public Claims getAllClaimsFromToken(String token){
        log.warn("token is " + token);

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    private boolean isTokenExpired(String token){
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }
    public boolean isInvalid(String token){
        return this.isTokenExpired(token);
    }
}
