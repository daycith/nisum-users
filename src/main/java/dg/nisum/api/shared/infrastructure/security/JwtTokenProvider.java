package dg.nisum.api.shared.infrastructure.security;

import dg.nisum.api.shared.domain.Service;
import dg.nisum.api.user.domain.TokenProvider;
import dg.nisum.api.user.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Service
public class JwtTokenProvider implements TokenProvider {

    @Value("#{environment.JWT_KEY}")
    private String jwtSecret;
    @Value("#{environment.EXPIRATION_TIME}")
    private int jwtExpirationMs;

    @Override
    public String generate(User user) {

        String jwt = Jwts.builder()
                .setSubject(user.getId().value())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return jwt;
    }
}
