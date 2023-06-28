package com.practicas.parcial2.Controllers;

import com.practicas.parcial2.Models.Game;
import com.practicas.parcial2.Models.User;
import com.practicas.parcial2.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;


    @PostMapping
    public ResponseEntity<String> playGame(@RequestBody String choice, User user) {
        return ResponseEntity.ok(gameService.playGame(choice,user));
    }

    @GetMapping
    public ResponseEntity<List<Game>> getGames(){
        return ResponseEntity.ok(gameService.getGames());
    }

}
