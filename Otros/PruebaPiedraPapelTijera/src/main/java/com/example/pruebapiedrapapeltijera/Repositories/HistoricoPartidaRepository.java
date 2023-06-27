package com.example.pruebapiedrapapeltijera.Repositories;

import com.example.pruebapiedrapapeltijera.Models.HistoricoPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoPartidaRepository extends JpaRepository<HistoricoPartida, Long> {
}
