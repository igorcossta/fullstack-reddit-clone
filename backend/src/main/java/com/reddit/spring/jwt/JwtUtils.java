package com.reddit.spring.jwt;

import com.reddit.spring.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.nio.charset.StandardCharsets.UTF_8;

public class JwtUtils {
    private static final String security = "djauisduiashduiashiudhasuidhuiashduiashduihasuidhasuid";
    private static final SecretKey key = hmacShaKeyFor(security.getBytes(UTF_8));

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static Set<SimpleGrantedAuthority> toGrantedAuthority(String token) {
        return extractAuthority(token).stream().map(x -> new SimpleGrantedAuthority(x.get("authority"))).collect(Collectors.toSet());
    }

    public static String createToken(User user) {
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(1);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(Timestamp.valueOf(expiration))
                .signWith(key)
                .compact();
    }

    private static <T> T extractClaim(String token, Function<Claims, T> f) {
        final Claims claims = extractAllClaims(token);
        return f.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return jws.getBody();
        } catch (JwtException e) {
            throw new RuntimeException("something went wrong with this token");
        }
    }

    private static List<Map<String, String>> extractAuthority(String token) {
        Claims claims = extractAllClaims(token);
        return (List<Map<String, String>>) claims.get("authorities");
    }
}
