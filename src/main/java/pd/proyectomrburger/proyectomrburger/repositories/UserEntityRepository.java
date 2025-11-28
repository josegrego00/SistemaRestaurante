package pd.proyectomrburger.proyectomrburger.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pd.proyectomrburger.proyectomrburger.models.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);

}
