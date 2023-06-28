package com.example.Tateti.Services;

import com.example.Tateti.Models.Game;
import com.example.Tateti.Models.Player;
import org.springframework.stereotype.Service;

@Service
public interface GameService {
    Game CreateGame(Player player1, Player player2);
    Game MakeInsertion(Long idGame, int col, int row);
}
