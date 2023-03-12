package dg.nisum.api.user.application.find;

import dg.nisum.api.shared.domain.Service;
import dg.nisum.api.user.domain.User;
import dg.nisum.api.user.domain.UserId;
import dg.nisum.api.user.domain.UserRepository;
import dg.nisum.api.user.domain.errors.UserNotFoundError;


@Service
public class UserFinder {
    private final UserRepository repository;

    public UserFinder(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto find(String userId) {
        User user = repository.findUserById(new UserId(userId))
                .orElseThrow(() -> new UserNotFoundError());

        return UserDtoMapper.fromAggregate(user);
    }
}
