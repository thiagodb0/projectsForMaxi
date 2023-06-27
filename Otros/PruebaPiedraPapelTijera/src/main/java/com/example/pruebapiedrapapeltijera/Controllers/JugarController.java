package com.example.pruebapiedrapapeltijera.Controllers;

import com.example.pruebapiedrapapeltijera.Models.HistoricoPartida;
import com.example.pruebapiedrapapeltijera.Models.Jugador;
import com.example.pruebapiedrapapeltijera.Service.JuegoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jugar")
public class JugarController {
    private final JuegoService juegoService;

    public JugarController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @PostMapping("/{mano}")
    public HistoricoPartida jugarPartida(@PathVariable String mano, @RequestBody Jugador jugador) {
        return juegoService.jugarPartida(mano, jugador);
    }
}
