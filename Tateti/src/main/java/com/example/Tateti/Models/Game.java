package com.example.Tateti.Models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private long Id;

    private String player1Name;

    private String player2Name;

    private GameState GameStatus;

    private Date FinishedAt;

    private String boardString;

    private Integer[][] Board;

}
