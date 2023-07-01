package tup.frc.practica.RPS.RPS.Services.impl;

import tup.frc.practica.RPS.RPS.Models.Board;
import tup.frc.practica.RPS.RPS.Models.SymbolsPlayer;
import tup.frc.practica.RPS.RPS.Services.BoardService;

public class BoardServiceImpl implements BoardService {
    @Override
    public Board createBoard() {
        String[][] board = new String[3][3];
        Board board1 = new Board();
        board1.setBoard(board);
        return board1;
    }

    @Override
    public String[][] insertSymbol(Integer row, Integer col, String[][] board, SymbolsPlayer symbolsPlayer) {
        board[row][col] = symbolsPlayer.toString();
        return board;
    }
}
