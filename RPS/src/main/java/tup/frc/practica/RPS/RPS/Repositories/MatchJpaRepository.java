package tup.frc.practica.RPS.RPS.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tup.frc.practica.RPS.RPS.Entities.MatchEntity;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchEntity, Long> {
}
