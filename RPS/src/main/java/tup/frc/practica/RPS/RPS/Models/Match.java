package tup.frc.practica.RPS.RPS.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tup.frc.practica.RPS.RPS.Entities.PlayerEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private Long id;


    private Board board;


    private PlayerEntity playerOne;


    private PlayerEntity playerTwo;


    private PlayerEntity nextToPlay;


    private PlayerEntity winner;


    private MatchStatus matchStatus;
}
