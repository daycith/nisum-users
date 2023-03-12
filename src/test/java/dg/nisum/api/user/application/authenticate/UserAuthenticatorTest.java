package dg.nisum.api.user.application.authenticate;

import dg.nisum.api.shared.domain.ForbiddenError;
import dg.nisum.api.shared.domain.PasswordEncrypter;
import dg.nisum.api.shared.infrastructure.security.JwtPasswordEncrypter;
import dg.nisum.api.user.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAuthenticatorTest {

    private UserRepository repository;

    private UserAuthenticator authenticator;
    private PasswordEncrypter passwordEncrypter;

    private TokenProvider tokenProvider;
    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        passwordEncrypter = new JwtPasswordEncrypter();
        tokenProvider = mock(TokenProvider.class);
        authenticator = new UserAuthenticator(repository, passwordEncrypter,tokenProvider);

    }

    @Test
    void authenticate_a_user_with_invalid_email() {

        UserEmail email = UserEmailMother.validRandom();
        UserPassword password = UserPasswordMother.random();

        when(repository.findByEmail(email)).thenReturn(Optional.empty());

        ForbiddenError exception = assertThrows(ForbiddenError.class, () ->{
            authenticator.authenticate(email.value(), password.value());
        });
    }

    @Test
    void authenticate_a_user_with_invalid_password() {

        UserEmail email = UserEmailMother.validRandom();
        UserPassword invalidPassword = UserPasswordMother.random();
        User user = UserMother.withEmail(email.value());

        when(repository.findByEmail(email)).thenReturn(Optional.of(user));

        ForbiddenError exception = assertThrows(ForbiddenError.class, () ->{
            authenticator.authenticate(email.value(), invalidPassword.value());
        });
    }

    @Test
    void authenticate_a_user_with_valid_credentials() {
        User user = UserMother.random();
        UserEmail email = user.getEmail();
        UserPassword password = user.getPassword();
        String expectedToken = "1123456789";

        when(repository.findByEmail(email)).thenReturn(Optional.of(user));
        when(tokenProvider.generate(user)).thenReturn(expectedToken);

        authenticator.authenticate(email.value(),password.value());

       assertUserIsAuthenticated(user,expectedToken);

       verify(repository).save(user);
    }

    private void assertUserIsAuthenticated(User user, String token){
        assertNotNull(user.getToken());
        assertEquals(token,user.getToken().value());
    }


}