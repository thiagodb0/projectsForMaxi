package com.example.Tateti.Controllers;

import com.example.Tateti.Entities.GameEntity;
import com.example.Tateti.Models.Game;
import com.example.Tateti.Models.Player;
import com.example.Tateti.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("")
    public ResponseEntity<Game> CreateGame(@RequestBody Player player1, Player player2) {
        return ResponseEntity.ok(gameService.CreateGame(player1,player2));
    }

    @PutMapping("/play/{idGame}")
    public ResponseEntity<Game> PlayGame(@PathVariable Long idGame, @RequestParam int col, @RequestParam int row) {
        return ResponseEntity.ok(gameService.MakeInsertion(idGame, col, row));
    }


}
