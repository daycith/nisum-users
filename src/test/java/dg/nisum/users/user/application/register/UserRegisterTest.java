package dg.nisum.users.user.application.register;

import dg.nisum.users.shared.domain.EventBus;
import dg.nisum.users.shared.domain.PasswordEncrypter;
import dg.nisum.users.user.domain.*;
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

    private EventBus eventBus;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        passwordEncrypter = mock(PasswordEncrypter.class);
        eventBus = mock(EventBus.class);
        userRegister = new UserRegister(repository, passwordEncrypter, eventBus);
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
    void register_a_valid_user() {

        RegisterUserRequest request = RegisterUserRequestMother.random();
        String encryptedPassword = UserPasswordMother.random().value();
        when(passwordEncrypter.encrypt(request.getPassword()))
                .thenReturn(encryptedPassword);
        User expectedUser = UserMother.fromRequestAndEncryptedPassword(request,encryptedPassword);
        UserRegisteredDomainEvent expectedEvent = UserRegisteredDomainEventMother.fromUser(expectedUser);

        userRegister.register(request);

        verify(repository, times(1)).save(expectedUser);
        verify(eventBus).publish(List.of(expectedEvent));
    }

    @Test
    void should_encrypt_the_password() {

        RegisterUserRequest request = RegisterUserRequestMother.random();
        String encryptedPassword = UserPasswordMother.random().value();
        when(passwordEncrypter.encrypt(request.getPassword())).thenReturn(encryptedPassword);

        userRegister.register(request);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(repository,times(1)).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();

        assertEquals(encryptedPassword, savedUser.getPassword().value());

    }

}