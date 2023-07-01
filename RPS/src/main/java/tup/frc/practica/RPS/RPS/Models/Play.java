package tup.frc.practica.RPS.RPS.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Play {
    private Long id;
    private Long matchId;
    private Player playerPlaying;
    private PlayerChoice choice;

}
