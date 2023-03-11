package dg.nisum.users.user.application.register;

import dg.nisum.users.shared.domain.EventBus;
import dg.nisum.users.shared.domain.PasswordEncrypter;
import dg.nisum.users.shared.infrastructure.security.JwtPasswordEncrypter;
import dg.nisum.users.user.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
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
        passwordEncrypter = new JwtPasswordEncrypter();
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
    void register_a_user_with_a_non_corporate_email() {

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
    void register_a_valid_user() {

        RegisterUserRequest request = RegisterUserRequestMother.random();
        User expectedUser = UserMother.fromRequest(request);
        UserRegisteredDomainEvent expectedEvent = UserRegisteredDomainEventMother.fromUser(expectedUser);

        userRegister.register(request);

        verify(repository, times(1)).save(expectedUser);
        verify(eventBus).publish(List.of(expectedEvent));
    }


}