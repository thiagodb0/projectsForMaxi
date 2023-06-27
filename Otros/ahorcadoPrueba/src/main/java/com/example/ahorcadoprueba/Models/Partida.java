package com.example.ahorcadoprueba.Models;

import jakarta.persistence.*;

@Entity
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    private String palabraAdivinar;
    private String palabraAdivinada;
    private String Estado;
    private int intentosRestantes;

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public String getPalabraAdivinar() {
        return palabraAdivinar;
    }

    public void setPalabraAdivinar(String palabraAdivinar) {
        this.palabraAdivinar = palabraAdivinar;
    }

    public String getPalabraAdivinada() {
        return palabraAdivinada;
    }

    public void setPalabraAdivinada(String palabraAdivinada) {
        this.palabraAdivinada = palabraAdivinada;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public void setIntentosRestantes(int intentosRestantes) {
        this.intentosRestantes = intentosRestantes;
    }
}
