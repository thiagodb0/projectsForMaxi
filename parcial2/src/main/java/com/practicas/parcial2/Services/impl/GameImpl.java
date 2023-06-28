package com.practicas.parcial2.Services.impl;

import com.practicas.parcial2.Entities.GameEntity;
import com.practicas.parcial2.Helpers.GameHelper;
import com.practicas.parcial2.Models.Game;
import com.practicas.parcial2.Models.User;
import com.practicas.parcial2.Repositories.GameJpaRepository;
import com.practicas.parcial2.Services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GameImpl implements GameService {
    @Autowired
    private GameJpaRepository gameJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    private GameHelper gameHelper = new GameHelper();

    @Override
    public String playGame(String playerChoice, User user) {
        String response = "No se pudo jugar";
        Game game = gameHelper.playGame(playerChoice, user);
        GameEntity gameEntity = modelMapper.map(game, GameEntity.class);
        gameJpaRepository.save(gameEntity);
        if (game.getWinner().equals("APP")) {
            response = "Perdiste :(, la app eligió " + game.getAppChoice() +
                    " y tú elegiste: " + game.getPlayerChoice();
        } else if (game.getWinner().equals("Draw")) {
            response = "Empate, la app eligió " + game.getAppChoice() +
                    " y tú elegiste: " + game.getPlayerChoice();
        } else if (game.getWinner().equals(user.getUserName())) {
            response = "¡Felicitaciones, ganaste! La app eligió " + game.getAppChoice() +
                    " y tú elegiste: " + game.getPlayerChoice();
        }

        return response;
    }

    @Override
    public List<Game> getGames() {
        List<GameEntity> gameEntities = gameJpaRepository.findAll();
        List<Game> games = new ArrayList<>();
        for (GameEntity gameEntity: gameEntities
             ) {
            Game game = modelMapper.map(gameEntity, Game.class);
            games.add(game);
        }
        return games;
    }
}
