package tup.frc.practica.RPS.RPS.Models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private Long id;
    private String userName;

    private String password;


    private String email;


    private Integer points;

    private SymbolsPlayer symbolsPlayer;
}
