package dg.nisum.api.user.application.register;

import dg.nisum.api.shared.domain.events.EventBus;
import dg.nisum.api.shared.domain.PasswordEncrypter;
import dg.nisum.api.user.domain.*;
import dg.nisum.api.user.domain.errors.UserEmailAlreadyExists;
import dg.nisum.api.user.domain.events.UserRegisteredDomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserRegisterTest {

    private UserRepository repository;

    private UserRegister userRegister;

    private PasswordEncrypter passwordEncrypter;

    private LadaKeyPatternConfiguration ladaKeyPatternConfiguration;

    private EventBus eventBus;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        passwordEncrypter = mock(PasswordEncrypter.class);
        ladaKeyPatternConfiguration = mock(LadaKeyPatternConfiguration.class);
        eventBus = mock(EventBus.class);
        userRegister = new UserRegister(repository, passwordEncrypter, ladaKeyPatternConfiguration,eventBus);

    }

    @Test
    void register_a_user_with_a_taken_email() {

        RegisterUserRequest request = RegisterUserRequestMother.random();
        String email = request.getEmail();
        User currentUser = UserMother.withEmail(email);

        when(repository.findByEmail(UserEmailMother.create(email))).thenReturn(Optional.of(currentUser));

        UserEmailAlreadyExists exception = assertThrows(
                UserEmailAlreadyExists.class,
                () -> userRegister.register(request
                )
        );

        assertEquals("El correo ya registrado", exception.getMessage());
    }

    @Test
    void register_a_user_with_an_external_email() {

        RegisterUserRequest request = RegisterUserRequestMother.withExternalEmail();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userRegister.register(request
                )
        );

        assertEquals("invalid email", exception.getMessage());
    }

    @Test
    void register_a_user_with_an_invalid_lada_key() {

        String countryCode = "500";
        String pattern = "^[0-9]{2}$";

        RegisterUserRequest request = RegisterUserRequestMother.withCountryCode(countryCode);
        when(ladaKeyPatternConfiguration.pattern()).thenReturn(pattern);
        when(passwordEncrypter.encrypt(anyString())).thenReturn(request.getPassword());
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userRegister.register(request
                )
        );

//        assertEquals("invalid lada key", exception.getMessage());
    }

    @Test
    void register_a_valid_user() {

        String validKey = "57";
        String pattern = "^[0-9]{2}$";

        RegisterUserRequest request = RegisterUserRequestMother.withCountryCode(validKey);
        String encryptedPassword = UserPasswordMother.random().value();
        when(passwordEncrypter.encrypt(request.getPassword()))
                .thenReturn(encryptedPassword);
        when(ladaKeyPatternConfiguration.pattern()).thenReturn(pattern);
        User expectedUser = UserMother.fromRequestAndEncryptedPassword(request,encryptedPassword);
        UserRegisteredDomainEvent expectedEvent = UserRegisteredDomainEventMother.fromUserAndCleanPassword(expectedUser, request.getPassword());

        userRegister.register(request);

        verify(repository, times(1)).save(expectedUser);
        verify(eventBus).publish(List.of(expectedEvent));
    }

    @Test
    void should_encrypt_the_password() {

        String validKey = "57";
        String pattern = "^[0-9]{2}$";

        RegisterUserRequest request = RegisterUserRequestMother.withCountryCode(validKey);
        String encryptedPassword = UserPasswordMother.random().value();
        when(passwordEncrypter.encrypt(request.getPassword())).thenReturn(encryptedPassword);
        when(ladaKeyPatternConfiguration.pattern()).thenReturn(pattern);

        userRegister.register(request);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(repository,times(1)).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();

        assertEquals(encryptedPassword, savedUser.getPassword().value());

    }

}