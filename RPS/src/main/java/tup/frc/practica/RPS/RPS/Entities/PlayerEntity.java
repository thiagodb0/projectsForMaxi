package tup.frc.practica.RPS.RPS.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tup.frc.practica.RPS.RPS.Models.SymbolsPlayer;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private Integer points;

    @Enumerated(EnumType.STRING)
    @Column
    private SymbolsPlayer symbolsPlayer;


}
