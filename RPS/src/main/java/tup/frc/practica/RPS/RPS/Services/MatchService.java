package tup.frc.practica.RPS.RPS.Services;

import org.springframework.stereotype.Service;
import tup.frc.practica.RPS.RPS.Models.Match;
import tup.frc.practica.RPS.RPS.Models.MatchStatus;
import tup.frc.practica.RPS.RPS.Models.Play;
import tup.frc.practica.RPS.RPS.Models.Player;
import tup.frc.practica.RPS.RPS.dtos.common.NewMatchRequestDTO;

@Service
public interface MatchService {
    Match createMatch(NewMatchRequestDTO newMatchRequestDTO);
    Match play(Play play);

    MatchStatus checkMatchStatus(Match match);
}
