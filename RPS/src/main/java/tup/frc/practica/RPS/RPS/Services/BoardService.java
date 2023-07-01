package tup.frc.practica.RPS.RPS.Services;

import jakarta.persistence.criteria.CriteriaBuilder;
import tup.frc.practica.RPS.RPS.Models.Board;
import tup.frc.practica.RPS.RPS.Models.SymbolsPlayer;

public interface BoardService {
    Board createBoard();
    String[][] insertSymbol(Integer row, Integer col, String[][] board, SymbolsPlayer symbolsPlayer);
}
