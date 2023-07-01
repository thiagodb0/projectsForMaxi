package tup.frc.practica.RPS.RPS.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tup.frc.practica.RPS.RPS.Models.Match;
import tup.frc.practica.RPS.RPS.Models.Play;
import tup.frc.practica.RPS.RPS.Services.MatchService;
import tup.frc.practica.RPS.RPS.dtos.common.NewMatchRequestDTO;

import java.util.Objects;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    MatchService matchService;

    @PostMapping("")
    public ResponseEntity<Match> saveMatch(@RequestBody @Valid NewMatchRequestDTO newMatchRequestDTO) {
        Match matchSaved = matchService.createMatch(newMatchRequestDTO);
        if(Objects.isNull(matchSaved)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request has an error");
        } else {
            return ResponseEntity.ok(matchSaved);
        }
    }

    @PostMapping("/{id}/plays/")
    public ResponseEntity<Match> saveMatch(@RequestBody @Valid Play playRequest) {
        return ResponseEntity.ok(matchService.play( playRequest));
    }
}
