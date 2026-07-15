package noemicoppotelli.appviaggiaziendali_backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import noemicoppotelli.appviaggiaziendali_backend.exceptions.UnauthorizedException;
import noemicoppotelli.appviaggiaziendali_backend.entities.Dipendente;
import java.util.Date;

@Component
public class JwtTools {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMs;

    public String generateToken(Dipendente dipendente) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(dipendente.getEmail())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String verify(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject(); //  ritorna l'email dell'utente
        } catch (Exception e) {
            throw new UnauthorizedException("Token non valido o scaduto");
        }
    }
}
