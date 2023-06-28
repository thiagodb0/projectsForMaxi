package com.practicas.parcial2.Services;

import com.practicas.parcial2.Models.Game;
import com.practicas.parcial2.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {
     String playGame(String playerChoice, User user);
     List<Game> getGames();

}
