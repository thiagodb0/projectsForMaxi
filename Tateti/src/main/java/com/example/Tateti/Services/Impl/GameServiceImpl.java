package com.example.Tateti.Services.Impl;

import com.example.Tateti.Configs.MapperConfig;
import com.example.Tateti.Entities.GameEntity;
import com.example.Tateti.Models.Game;
import com.example.Tateti.Models.GameState;
import com.example.Tateti.Models.Player;
import com.example.Tateti.Repositories.GameRepository;
import com.example.Tateti.Services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private static final int PLAYER1 = 1;
    private static final int PLAYER2 = 2;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GameRepository gameRepository;
    

    public Game CreateGame(Player player1, Player player2){
        Game game = new Game();
        game.setBoard(initBoard());
        game.setPlayer1Name(player1.getUserName());
        game.setPlayer2Name(player2.getUserName());
        game.setGameStatus(GameState.PLAYER1TURN);
        game.setBoardString(boardToString(game.getBoard()));
        GameEntity gameEntity = modelMapper.map(game, GameEntity.class);
        GameEntity gameSaved = gameRepository.save(gameEntity);
        return modelMapper.map(gameSaved, Game.class);
    }

    public Game MakeInsertion(Long idGame, int col, int row) {
        Optional<GameEntity> gameEntity = gameRepository.findById(idGame);
        Game game = modelMapper.map(gameEntity, Game.class);
        game.setBoard(stringToBoard(game.getBoardString()));
        game.setBoard(insertSymbol(col, row, game));
        GameState gameState = CheckStatus(game);
        game.setGameStatus(gameState);
        game.setBoardString(boardToString(game.getBoard()));
        GameEntity gameEntity1 = modelMapper.map(game, GameEntity.class);
        GameEntity gameUpd = gameRepository.save(gameEntity1);
        return modelMapper.map(gameUpd, Game.class);
    }
    public Integer [][] insertSymbol(int col, int row, Game game){
        Integer[][] board = game.getBoard();
        GameState state = game.getGameStatus();
        if(state.equals(GameState.PLAYER1TURN)) {
            board[row][col] = PLAYER1;
            game.setGameStatus(GameState.PLAYER2TURN);
        }
        else {
            board[row][col] = PLAYER2;
            game.setGameStatus(GameState.PLAYER1TURN);
        }
                    
        return board;
    }
    public Integer[][] initBoard (){
        Integer[][] board = new Integer[3][3];
        return board;
    }
    
    public GameState CheckStatus(Game game){
        GameState gameStateOld = game.getGameStatus();
        GameState gameState = game.getGameStatus();
        Integer [][] board = game.getBoard();
        gameState = checkLines(board, gameState);
       if (gameState== gameStateOld) {
           gameState = checkDiagonals(board, gameState);
       }
       return gameState;
    }

    public String boardToString(Integer[][] board) {
        StringBuilder sb = new StringBuilder();
        for (Integer[] row : board) {
            for (Integer cell : row) {
                sb.append(cell != null ? cell.toString() : "-"); //
            }
        }
        return sb.toString();
    }

    public Integer[][] stringToBoard(String boardString) {
        Integer[][] board = new Integer[3][3];
        int index = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                char symbol = boardString.charAt(index);
                if (symbol != '-') {
                    board[row][col] = Character.getNumericValue(symbol);
                }
                index++;
            }
        }
        return board;
    }




    private GameState checkLines(Integer[][] board, GameState gameState) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) {
                if (board[i][0].equals(PLAYER1))
                    gameState = GameState.PLAYER1WIN;
                else if (board[i][0].equals(PLAYER2))
                    gameState = GameState.PLAYER2WIN;
            }

            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])) {
                if (board[0][i].equals(PLAYER1))
                    gameState = GameState.PLAYER1WIN;
                else if (board[0][i].equals(PLAYER2))
                    gameState = GameState.PLAYER2WIN;
            }
        }

        return gameState;
    }

    private GameState checkDiagonals(Integer[][] board, GameState gameState) {
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            if (board[0][0].equals(PLAYER1))
                gameState = GameState.PLAYER1WIN;
            else if (board[0][0].equals(PLAYER2))
                gameState = GameState.PLAYER2WIN;
        }

        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            if (board[0][2].equals(PLAYER1))
                gameState = GameState.PLAYER1WIN;
            else if (board[0][2].equals(PLAYER2))
                gameState = GameState.PLAYER2WIN;
        }

        return gameState;
    }
}
