package dg.nisum.api.shared.infrastructure.security;

import dg.nisum.api.shared.domain.PasswordEncrypter;
import dg.nisum.api.shared.domain.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class JwtPasswordEncrypter implements PasswordEncrypter {

    @Override
    public String encrypt(String cleanValue) {
        return new BCryptPasswordEncoder().encode(cleanValue);
    }
}
