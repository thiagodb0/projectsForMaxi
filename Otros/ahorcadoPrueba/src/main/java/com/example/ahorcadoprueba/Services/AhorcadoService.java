package com.example.ahorcadoprueba.Services;

import com.example.ahorcadoprueba.Enums.EstadoPartida;
import com.example.ahorcadoprueba.Exceptions.InvalidGameStateException;
import com.example.ahorcadoprueba.Exceptions.NotFoundException;
import com.example.ahorcadoprueba.Models.Jugador;
import com.example.ahorcadoprueba.Models.Partida;
import com.example.ahorcadoprueba.Repositories.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AhorcadoService {
    private final PartidaRepository partidaRepository;

    public AhorcadoService(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    public Partida crearPartida(Jugador jugador) {
        String palabraAleatoria = generarPalabraAleatoria();

        Partida partida = new Partida();
        partida.setJugador(jugador);
        partida.setPalabraAdivinar(palabraAleatoria);
        partida.setPalabraAdivinada(generarPalabraVacia(palabraAleatoria));
        partida.setIntentosRestantes(6);

        return partidaRepository.save(partida);
    }

    public Partida obtenerPartida(Long partidaId) {
        return partidaRepository.findById(partidaId)
                .orElseThrow(() -> new NotFoundException("Partida no encontrada con ID: " + partidaId));
    }

    public Partida realizarJugada(Long partidaId, String letra) {
        Partida partida = partidaRepository.findById(partidaId)
                .orElseThrow(() -> new NotFoundException("Partida no encontrada con ID: " + partidaId));

        if (partida.getIntentosRestantes() <= 0) {
            throw new InvalidGameStateException("La partida ha terminado. No se pueden realizar más jugadas.");
        }

        String palabraAdivinar = partida.getPalabraAdivinar();
        String palabraAdivinada = partida.getPalabraAdivinada();
        int intentosRestantes = partida.getIntentosRestantes();

        StringBuilder sb = new StringBuilder(palabraAdivinada);
        boolean letraAgregada = false;

        for (int i = 0; i < palabraAdivinar.length(); i++) {
            if (palabraAdivinar.charAt(i) == letra.charAt(0) && palabraAdivinada.charAt(i) == '_') {
                sb.setCharAt(i, letra.charAt(0));
                letraAgregada = true;
            }
        }

        if (!letraAgregada) {
            intentosRestantes--;
        }

        palabraAdivinada = sb.toString();

        partida.setPalabraAdivinada(palabraAdivinada);
        partida.setIntentosRestantes(intentosRestantes);

        actualizarEstadoPartida(partida);

        partidaRepository.save(partida);

        return partida;
    }


    private void actualizarEstadoPartida(Partida partida) {
        String palabraAdivinar = partida.getPalabraAdivinar();
        String palabraAdivinada = partida.getPalabraAdivinada();
        int intentosRestantes = partida.getIntentosRestantes();

        if (palabraAdivinada.equals(palabraAdivinar)) {
            partida.setEstado(String.valueOf(EstadoPartida.GANADA));
            partida.setIntentosRestantes(0);
        } else if (intentosRestantes <= 0) {
            partida.setEstado(String.valueOf(EstadoPartida.PERDIDA));
            partida.setIntentosRestantes(0);
        } else {
            partida.setEstado(String.valueOf(EstadoPartida.EN_PROGRESO));
        }
    }







    private String generarPalabraAleatoria() {
        String[] palabras = {"programacion", "computadora", "java", "primavera", "telefono", "libro"};

        Random random = new Random();
        int indice = random.nextInt(palabras.length);

        return palabras[indice];
    }

    private String generarPalabraVacia(String palabra) {
        StringBuilder palabraVacia = new StringBuilder();
        for (int i = 0; i < palabra.length(); i++) {
            palabraVacia.append("_");
        }
        return palabraVacia.toString();
    }

    private String reemplazarLetra(String palabra, int indice, char letra) {
        char[] palabraArray = palabra.toCharArray();
        palabraArray[indice] = letra;
        return new String(palabraArray);
    }
}


