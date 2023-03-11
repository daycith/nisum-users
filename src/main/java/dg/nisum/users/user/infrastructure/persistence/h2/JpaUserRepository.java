package dg.nisum.users.user.infrastructure.persistence.h2;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
//    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByEmailIgnoreCase(String email);
}
