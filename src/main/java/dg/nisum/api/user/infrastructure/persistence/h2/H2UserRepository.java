package dg.nisum.api.user.infrastructure.persistence.h2;

import dg.nisum.api.shared.domain.Service;
import dg.nisum.api.user.domain.User;
import dg.nisum.api.user.domain.UserEmail;
import dg.nisum.api.user.domain.UserId;
import dg.nisum.api.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.Optional;

@Primary
@Service
public class H2UserRepository implements UserRepository {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Override
    public void save(User user) {
        UserEntity userEntity = UserMapper.fromAggregate(user);
        jpaUserRepository.save(userEntity);
    }

    @Override
    public Optional<User> findUserById(UserId id) {

        Optional<UserEntity> userEntity = jpaUserRepository.findById(id.value());

        return userEntity.map(UserMapper::fromEntity);

    }

    @Override
    public Optional<User> findByEmail(UserEmail email) {
        Optional<UserEntity> userEntity = jpaUserRepository.findByEmailIgnoreCase(email.value());

        return userEntity.map(UserMapper::fromEntity);

    }

    @Override
    public void deleteAll() {
        jpaUserRepository.deleteAll();
    }

}
