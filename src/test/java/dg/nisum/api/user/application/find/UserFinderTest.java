package dg.nisum.api.user.application.find;

import dg.nisum.api.user.domain.*;
import dg.nisum.api.user.domain.errors.UserNotFoundError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserFinderTest {

    private UserRepository repository;
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        userFinder = new UserFinder(repository);
    }

    @Test
    void find_an_invalid_user(){

        UserId userId = UserIdMother.random();

        when(repository.findUserById(userId)).thenReturn(Optional.empty());

        UserNotFoundError exception = assertThrows(UserNotFoundError.class, () -> {
            userFinder.find(userId.value());
        });

        assertEquals("user not found", exception.getMessage());
    }

    @Test
    void find_a_valid_user() {
        User user = UserMother.random();

        when(repository.findUserById(user.getId())).thenReturn(Optional.of(user));

        UserDto expectedUserDto = UserDtoMapper.fromAggregate(user);

        UserDto userDto = userFinder.find(user.getId().value());

        assertEquals(expectedUserDto.getId(),userDto.getId());
        assertEquals(expectedUserDto.getName(),userDto.getName());
        assertEquals(expectedUserDto.getEmail(),userDto.getEmail());
        assertEquals(expectedUserDto.getCreated(),userDto.getCreated());
        assertEquals(expectedUserDto.getLastLogin(),userDto.getLastLogin());
    }
}