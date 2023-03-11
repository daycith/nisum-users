package dg.nisum.users.user.infrastructure.persistence;

import dg.nisum.users.shared.domain.Service;
import dg.nisum.users.user.domain.User;
import dg.nisum.users.user.domain.UserEmail;
import dg.nisum.users.user.domain.UserId;
import dg.nisum.users.user.domain.UserRepository;

import java.util.HashMap;
import java.util.Optional;

@Service
public class InMemoryUserRepository implements UserRepository {
    private HashMap<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getId().value(), user);
    }

    @Override
    public Optional<User> findUserById(UserId id) {
        return Optional.ofNullable(users.get(id.value()));
    }

    @Override
    public Optional<User> findByEmail(UserEmail email) {

        return users.values()
                .stream()
                .filter(user -> {
                    return user.getEmail().equals(email);
                })
                .findFirst();
    }

    @Override
    public void deleteAll() {
        users = new HashMap<>();
    }

}
