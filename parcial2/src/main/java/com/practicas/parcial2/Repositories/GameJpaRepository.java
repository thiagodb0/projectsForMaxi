package com.practicas.parcial2.Repositories;

import com.practicas.parcial2.Entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameJpaRepository extends JpaRepository<GameEntity, Long> {
}
