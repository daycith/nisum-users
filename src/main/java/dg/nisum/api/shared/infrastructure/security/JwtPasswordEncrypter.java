package dg.nisum.api.shared.infrastructure.security;

import dg.nisum.api.shared.domain.PasswordEncrypter;
import dg.nisum.api.shared.domain.Service;

@Service
public class JwtPasswordEncrypter implements PasswordEncrypter {

    @Override
    public String encrypt(String cleanValue) {
        return cleanValue; //TODO: encrypt the password
    }
}
