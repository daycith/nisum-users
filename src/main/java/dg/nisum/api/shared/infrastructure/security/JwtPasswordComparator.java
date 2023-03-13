package dg.nisum.api.shared.infrastructure.security;

import dg.nisum.api.shared.domain.PasswordComparator;
import dg.nisum.api.shared.domain.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Service
public class JwtPasswordComparator implements PasswordComparator {

    @Override
    public boolean compare(String plainPassword, String userPassword) {
        return BCrypt.checkpw(plainPassword,userPassword);
    }
}
