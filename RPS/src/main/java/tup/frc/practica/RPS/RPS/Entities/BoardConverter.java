package tup.frc.practica.RPS.RPS.Entities;

import jakarta.persistence.AttributeConverter;
import org.modelmapper.Converter;
import tup.frc.practica.RPS.RPS.Models.Board;

public class BoardConverter implements AttributeConverter<Board, String> {
    @Override
    public String convertToDatabaseColumn(Board board) {
        String [][] boardStructure = board.getBoard();
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] row: boardStructure
             ) {
            for (String cell: row
                 ) {
                stringBuilder.append(cell!=null? cell : "-");
            }

        }
        return stringBuilder.toString();
    }

    @Override
    public Board convertToEntityAttribute(String s) {
        String[][] loadedBoard = new String[3][3];
        int index = 0;
        for (int row = 0; row < loadedBoard.length; row++) {
            for (int col = 0; col < loadedBoard[row].length; col++) {
                String symbol = String.valueOf(s.charAt(index));
                if (symbol.equals("-")) {
                    loadedBoard[row][col] = symbol;
                }
                index++;
            }
        }
        Board board = new Board();
        board.setBoard(loadedBoard);
        return board;
    }
}
