package com.example.ahorcadoprueba.Controllers;

import com.example.ahorcadoprueba.Enums.EstadoPartida;
import com.example.ahorcadoprueba.Models.Jugador;
import com.example.ahorcadoprueba.Models.Partida;
import com.example.ahorcadoprueba.Repositories.PartidaRepository;
import com.example.ahorcadoprueba.Services.AhorcadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/partidas")
public class PartidaController {
    private final PartidaRepository partidaRepository;
    private final AhorcadoService ahorcadoService;

    public PartidaController(PartidaRepository partidaRepository, AhorcadoService ahorcadoService) {
        this.partidaRepository = partidaRepository;
        this.ahorcadoService = ahorcadoService;
    }

    @PostMapping
    public Partida crearPartida(@RequestBody Jugador jugador) {
        return ahorcadoService.crearPartida(jugador);
    }

    @GetMapping("/{id}")
    public Partida obtenerPartida(@PathVariable Long id) {
        return ahorcadoService.obtenerPartida(id);
    }

    @PostMapping("/{id}/jugadas")
    public ResponseEntity<Map<String, Object>> realizarJugada(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String letra = requestBody.get("letra");
        Partida partida = ahorcadoService.realizarJugada(id, letra);

        Map<String, Object> response = new HashMap<>();
        response.put("palabraAdivinar", partida.getPalabraAdivinar());
        response.put("palabraAdivinada", partida.getPalabraAdivinada());
        response.put("intentosRestantes", partida.getIntentosRestantes());
        response.put("estado", partida.getEstado());

        if (partida.getEstado().equals(EstadoPartida.GANADA)) {
            return ResponseEntity.ok(response);
        } else if (partida.getEstado().equals(EstadoPartida.PERDIDA)) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }





}

