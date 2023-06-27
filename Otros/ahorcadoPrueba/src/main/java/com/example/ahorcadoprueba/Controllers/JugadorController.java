package com.example.ahorcadoprueba.Controllers;

import com.example.ahorcadoprueba.Models.Jugador;
import com.example.ahorcadoprueba.Repositories.JugadorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {
    private final JugadorRepository jugadorRepository;

    public JugadorController(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    @PostMapping("/")
    public Jugador crearJugador(@RequestBody Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @GetMapping("/")
    public List<Jugador> listarJugadores() {
        return jugadorRepository.findAll();
    }
}
