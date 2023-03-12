package dg.nisum.api.user.infrastructure.persistence;

import dg.nisum.api.shared.domain.Service;
import dg.nisum.api.user.domain.User;
import dg.nisum.api.user.domain.UserEmail;
import dg.nisum.api.user.domain.UserId;
import dg.nisum.api.user.domain.UserRepository;

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
