package dg.nisum.api.shared.infrastructure.security;

import dg.nisum.api.shared.domain.PasswordEncrypter;
import dg.nisum.api.user.domain.UserPassword;
import dg.nisum.api.user.domain.UserPasswordMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtPasswordEncrypterTest {

    @Autowired
    PasswordEncrypter passwordEncrypter;

    @Test
    void encrypt() {
        String password = UserPasswordMother.random().value();

        String salt = passwordEncrypter.encrypt(password);

        assertTrue(BCrypt.checkpw(password,salt));

    }
}