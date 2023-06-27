package com.example.pruebapiedrapapeltijera.Repositories;

import com.example.pruebapiedrapapeltijera.Models.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}
