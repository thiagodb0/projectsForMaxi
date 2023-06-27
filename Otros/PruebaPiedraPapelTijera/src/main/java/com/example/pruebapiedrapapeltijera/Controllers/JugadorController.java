package com.example.pruebapiedrapapeltijera.Controllers;

import com.example.pruebapiedrapapeltijera.Exceptions.NotFoundException;
import com.example.pruebapiedrapapeltijera.Models.Jugador;
import com.example.pruebapiedrapapeltijera.Repositories.JugadorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
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

    @GetMapping("/{id}")
    public Jugador mostrarJugador(@PathVariable Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Jugador no encontrado con ID: " + id));
    }

    @PutMapping("/{id}")
    public Jugador editarJugador(@PathVariable Long id, @RequestBody Jugador jugadorDatos) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Jugador no encontrado con ID: " + id));

        jugador.setNombre(jugadorDatos.getNombre());
        jugador.setNpc(jugadorDatos.isNpc());

        return jugadorRepository.save(jugador);
    }

    @DeleteMapping("/{id}")
    public void eliminarJugador(@PathVariable Long id) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Jugador no encontrado con ID: " + id));

        jugadorRepository.delete(jugador);
    }
}
