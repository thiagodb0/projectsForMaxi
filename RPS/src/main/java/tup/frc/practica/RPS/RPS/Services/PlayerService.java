package tup.frc.practica.RPS.RPS.Services;

import tup.frc.practica.RPS.RPS.Models.Player;

public interface PlayerService {
     Player getPlayerById(Long id);
     Player savePlayer(Player player);
}
