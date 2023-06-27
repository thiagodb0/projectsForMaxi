package com.example.ahorcadoprueba.Repositories;

import com.example.ahorcadoprueba.Models.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
}
