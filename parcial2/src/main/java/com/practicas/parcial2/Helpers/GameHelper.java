package com.practicas.parcial2.Helpers;

import com.practicas.parcial2.Models.Game;
import com.practicas.parcial2.Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameHelper {

    public String getAppChoice(){
        List<String> choices = new ArrayList<>();
        choices.add("Rock");
        choices.add("Paper");
        choices.add("Scissors");
        int randomNumber = (int) (Math.random() * 3);
        return choices.get(randomNumber);
    }

    public Game playGame(String playerChoice, User user){
        String appChoice = getAppChoice();
        Game game = new Game();
        game.setAppChoice(appChoice);
        game.setPlayerChoice(playerChoice);
        if ((Objects.equals(appChoice, "Rock") && Objects.equals(playerChoice, "Scissors"))
                || (Objects.equals(appChoice, "Paper") && Objects.equals(playerChoice, "Rock"))
                || (Objects.equals(appChoice, "Scissors") && Objects.equals(playerChoice, "Paper"))) {
            game.setWinner("APP");
        } else if (Objects.equals(appChoice, playerChoice)) {
            game.setWinner("Draw");
        } else {
            game.setWinner(user.getUserName());
        }
        return game;
    }
}
