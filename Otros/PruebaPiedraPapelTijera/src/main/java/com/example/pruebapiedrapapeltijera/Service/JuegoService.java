package com.example.pruebapiedrapapeltijera.Service;

import com.example.pruebapiedrapapeltijera.Models.HistoricoPartida;
import com.example.pruebapiedrapapeltijera.Models.Jugador;
import com.example.pruebapiedrapapeltijera.Repositories.HistoricoPartidaRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class JuegoService {
    private final HistoricoPartidaRepository historicoPartidaRepository;

    public JuegoService(HistoricoPartidaRepository historicoPartidaRepository) {
        this.historicoPartidaRepository = historicoPartidaRepository;
    }

    public HistoricoPartida jugarPartida(String mano, Jugador jugador) {
        String resultado;
        String jugada;

        String jugadaJugador = mano.toLowerCase();
        String jugadaNPC = generarJugadaNPC();

        if (jugadaJugador.equals(jugadaNPC)) {
            resultado = "Empate";
        } else if ((jugadaJugador.equals("piedra") && jugadaNPC.equals("tijera")) ||
                (jugadaJugador.equals("papel") && jugadaNPC.equals("piedra")) ||
                (jugadaJugador.equals("tijera") && jugadaNPC.equals("papel"))) {
            resultado = "Ganaste";
        } else {
            resultado = "Perdiste";
        }

        jugada = "Jugador: " + jugadaJugador + ", NPC: " + jugadaNPC;

        HistoricoPartida historicoPartida = new HistoricoPartida();
        historicoPartida.setJugador(jugador);
        historicoPartida.setResultado(resultado);
        historicoPartida.setJugada(jugada);

        return historicoPartidaRepository.save(historicoPartida);
    }

    private String generarJugadaNPC() {
        String[] jugadas = {"piedra", "papel", "tijera"};
        Random random = new Random();
        int index = random.nextInt(jugadas.length);
        return jugadas[index];
    }
}
