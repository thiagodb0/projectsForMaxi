package com.example.pruebapiedrapapeltijera.Controllers;

import com.example.pruebapiedrapapeltijera.Exceptions.NotFoundException;
import com.example.pruebapiedrapapeltijera.Models.HistoricoPartida;
import com.example.pruebapiedrapapeltijera.Repositories.HistoricoPartidaRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jugadas")
public class JugadasController {
    private final HistoricoPartidaRepository historicoPartidaRepository;

    public JugadasController(HistoricoPartidaRepository historicoPartidaRepository) {
        this.historicoPartidaRepository = historicoPartidaRepository;
    }

    @GetMapping("/")
    public List<HistoricoPartida> listarJugadas() {
        return historicoPartidaRepository.findAll();
    }

    @GetMapping("/{id}")
    public HistoricoPartida mostrarJugada(@PathVariable Long id) {
        return historicoPartidaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Jugada no encontrada con ID: " + id));
    }
}
