package tup.frc.practica.RPS.RPS.Services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tup.frc.practica.RPS.RPS.Entities.MatchEntity;
import tup.frc.practica.RPS.RPS.Entities.PlayerEntity;
import tup.frc.practica.RPS.RPS.Models.*;
import tup.frc.practica.RPS.RPS.Repositories.MatchJpaRepository;
import tup.frc.practica.RPS.RPS.Services.BoardService;
import tup.frc.practica.RPS.RPS.Services.MatchService;
import tup.frc.practica.RPS.RPS.Services.PlayerService;
import tup.frc.practica.RPS.RPS.dtos.common.NewMatchRequestDTO;

import java.util.Objects;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MatchJpaRepository matchJpaRepository;
    @Autowired
    PlayerService playerService;

    BoardService boardService = new BoardServiceImpl();
    @Override
    public Match createMatch(NewMatchRequestDTO newMatchRequestDTO) {
        Player player1 = playerService.getPlayerById(newMatchRequestDTO.getPlayerOneId());
        Player player2 = playerService.getPlayerById(newMatchRequestDTO.getPlayerTwoId());
        MatchEntity matchEntity = initMatch(player1, player2);
        matchEntity = matchJpaRepository.save(matchEntity);
        if(Objects.isNull(matchEntity)){
            throw new RuntimeException("The server couldn't create the match");
        }else
            return modelMapper.map(matchEntity, Match.class);
    }

    @Override
    public Match play(Play play) {
        Optional<MatchEntity> matchEntity = matchJpaRepository.findById(play.getMatchId());
        if(!matchEntity.isPresent())
        {
            throw new EntityNotFoundException("No se encontro el juego");
        }
        Player playerEntity = playerService.getPlayerById(play.getPlayerPlaying().getId());
        Match match = modelMapper.map(matchEntity,Match.class);
        Board board1 = match.getBoard();
        String[][] board = board1.getBoard();
        board = boardService.insertSymbol(play.getChoice().getRow(), play.getChoice().getCol(),board ,playerEntity.getSymbolsPlayer());
        board1.setBoard(board);
        match.setBoard(board1);
        match.setMatchStatus(checkMatchStatus(match));
        if(match.getMatchStatus().equals(MatchStatus.FINISHED)){
            match.setWinner(modelMapper.map(playerEntity, PlayerEntity.class));
        }
        return modelMapper.map(match, Match.class);
    }

    @Override
    public MatchStatus checkMatchStatus(Match match) {
        MatchStatus gameStateOld = match.getMatchStatus();
        MatchStatus gameState = match.getMatchStatus();
        String [][] board = match.getBoard().getBoard();
        gameState = checkLines(board, gameState);
        if (gameState== gameStateOld) {
            gameState = checkDiagonals(board, gameState);
        }
        return gameState;
    }

    private MatchEntity initMatch(Player player1, Player player2){
        MatchEntity match = new MatchEntity();
        player2.setSymbolsPlayer(SymbolsPlayer.O);
        match.setMatchStatus(MatchStatus.PLAYING);
        Board board = boardService.createBoard();
        match.setBoard(board);
        match.setPlayerOne(modelMapper.map(player1, PlayerEntity.class));
        match.setPlayerTwo(modelMapper.map(player2, PlayerEntity.class));
        match.setNextToPlay(modelMapper.map(player1, PlayerEntity.class));
        return match;
    }

    private MatchStatus checkLines(String[][] board, MatchStatus gameState) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) {
                if(board[i][0] != null){
                    gameState= MatchStatus.FINISHED;
                }
            }

            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])) {
                if(board[0][i] != null){
                    gameState= MatchStatus.FINISHED;
                }
            }
        }

        return gameState;
    }

    private MatchStatus checkDiagonals(String[][] board, MatchStatus gameState) {
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            if(board[0][0] != null){
                gameState= MatchStatus.FINISHED;
            }
        }

        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            if(board[0][2] != null){
                gameState= MatchStatus.FINISHED;
            }
        }

        return gameState;
    }


}
