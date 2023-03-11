package dg.nisum.users.shared.infrastructure.security;

import dg.nisum.users.shared.domain.PasswordEncrypter;
import dg.nisum.users.shared.domain.Service;

@Service
public class JwtPasswordEncrypter implements PasswordEncrypter {

    @Override
    public String encrypt(String cleanValue) {
        return cleanValue; //TODO: encrypt the password
    }
}
