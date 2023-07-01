package tup.frc.practica.RPS.RPS.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tup.frc.practica.RPS.RPS.Models.Board;
import tup.frc.practica.RPS.RPS.Models.MatchStatus;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = BoardConverter.class)
    @Column
    private Board board;

    @JoinColumn(name="player_one_id")
    @ManyToOne
    private PlayerEntity playerOne;

    @JoinColumn(name="player_two_id")
    @ManyToOne
    private PlayerEntity playerTwo;

    @JoinColumn(name="next_to_play_id")
    @ManyToOne
    private PlayerEntity nextToPlay;

    @JoinColumn(name="winner_id")
    @ManyToOne
    private PlayerEntity winner;

    @Column
    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus;
}
