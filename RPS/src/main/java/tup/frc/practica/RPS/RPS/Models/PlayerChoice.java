package tup.frc.practica.RPS.RPS.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerChoice {
    private Integer col;
    private Integer row;
}
