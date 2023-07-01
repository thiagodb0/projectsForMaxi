package tup.frc.practica.RPS.RPS.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tup.frc.practica.RPS.RPS.Entities.PlayerEntity;

import java.util.Optional;


@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findByUserNameOrEmail(String userName, String email);
}
