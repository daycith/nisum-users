package dg.nisum.users.shared.infrastructure.security;

import dg.nisum.users.shared.domain.Service;
import dg.nisum.users.user.domain.TokenProvider;
import dg.nisum.users.user.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Service
public class JwtTokenProvider implements TokenProvider {

    @Value("${users.JWT_KEY}")
    private String jwtSecret;
    @Value("${users.EXPIRATION_TIME}")
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
