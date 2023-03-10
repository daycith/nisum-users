package dg.nisum.users.user.application.find;

import dg.nisum.users.user.domain.User;
import dg.nisum.users.user.domain.UserId;
import dg.nisum.users.user.domain.UserNotFoundError;
import dg.nisum.users.user.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserFinder {
    private final UserRepository repository;

    public UserFinder(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto find(String userId) {
        User user = repository.findUserById(new UserId(userId))
                .orElseThrow(() -> new UserNotFoundError(userId));

        return UserDtoMapper.fromAggregate(user);
    }
}
