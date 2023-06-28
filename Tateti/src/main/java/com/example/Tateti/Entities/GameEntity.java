package com.example.Tateti.Entities;

import com.example.Tateti.Models.GameState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Games")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column
    private String player1Name;

    @Column
    private String player2Name;

    @Column
    private GameState GameStatus;

    @Column
    private Date FinishedAt;
    @Column
    private String boardString;
}
