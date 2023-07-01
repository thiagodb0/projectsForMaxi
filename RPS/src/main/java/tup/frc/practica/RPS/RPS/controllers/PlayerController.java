package tup.frc.practica.RPS.RPS.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tup.frc.practica.RPS.RPS.Models.Player;
import tup.frc.practica.RPS.RPS.Services.PlayerService;
import tup.frc.practica.RPS.RPS.dtos.common.ErrorApi;

import java.util.Objects;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @Operation(
            summary = "Get a player by id",
            description = "Return a player by they id. If the player doesn't exist return 404")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        return ResponseEntity.ok(player);
    }

    @Operation(
            summary = "Create a new player",
            description = "Return the player created with your id. If a player exist with the username or email, then return 404. " +
                    "Additionally, the email must be valid and the password must have at least 8 characters and contain at least one number, " +
                    "one lowercase letter, one uppercase letter and one special character.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "400", description = "Username or email already exists", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @PostMapping("")
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player newPlayer) {
        Player playerSaved = playerService.savePlayer(newPlayer);
            return ResponseEntity.ok(playerSaved);

    }

}
