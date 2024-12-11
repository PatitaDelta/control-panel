package es.guillermoll.control_panel.auth.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "0a4b1f4bac45a769de32c6e4fc3076a416abb1f44def15a48dce8e0e7b3518d5";

    public String getToken(UserDetails user) {
        return getTokenJwts(new HashMap<>(), user);
    }

    private String getTokenJwts(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                // .claim("claim1", "value1")
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }
}
