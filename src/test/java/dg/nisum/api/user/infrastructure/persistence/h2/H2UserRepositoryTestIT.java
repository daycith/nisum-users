package dg.nisum.api.user.infrastructure.persistence.h2;

import dg.nisum.api.user.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class H2UserRepositoryTestIT {

    @Autowired
    UserRepository repository;

    @Test
    void save_user() {
        User user = UserMother.random();
        assertDoesNotThrow(() -> {
            repository.save(user);
        });
    }

    @Test
    void find_an_existing_user_by_id() {
        User user = UserMother.random();

        repository.save(user);

        User savedUser = repository.findUserById(user.getId()).orElse(null);

        assertNotNull(savedUser);
    }

    @Test
    void find_an_existing_user_by_email() {
        User user = UserMother.random();

        repository.save(user);

        User savedUser = repository.findByEmail(user.getEmail()).orElse(null);

        assertNotNull(savedUser);
    }


}