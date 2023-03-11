package dg.nisum.users.shared.infrastructure.security;

import dg.nisum.users.shared.domain.Service;
import dg.nisum.users.user.domain.TokenProvider;
import dg.nisum.users.user.domain.User;

import java.util.UUID;

@Service
public class JwtTokenProvider implements TokenProvider {

    @Override
    public String generate(User user) {
        return UUID.randomUUID().toString();
    }
}
