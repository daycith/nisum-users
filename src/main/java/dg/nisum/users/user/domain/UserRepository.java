package dg.nisum.users.user.domain;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findUserById(UserId id);

    Optional<User> findByEmail(UserEmail email);

    void deleteAll();
}
