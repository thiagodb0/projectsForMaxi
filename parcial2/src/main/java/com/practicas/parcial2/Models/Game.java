package com.practicas.parcial2.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private long id;
    private String winner;

    private String playerChoice;

    private String appChoice;
    private Date fecha;
}
