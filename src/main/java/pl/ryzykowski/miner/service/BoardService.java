package pl.ryzykowski.miner.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ryzykowski.miner.config.Globals;
import pl.ryzykowski.miner.data.Board;

import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class BoardService {

    private final CellService cellService;

    public int[][] createBoard(){
        Board board = new Board();
        board = clearBoard(board);
        board = fillBoardWithBombs(board);
        board = fillBoardWithNumbers(board);
        return board.getGameBoard();
    }

    private Board clearBoard(Board board){
        for (int i = 0; i< Globals.BOARD_DIMENSION; i++) {
            for (int j=0; j<Globals.BOARD_DIMENSION; j++) {
                board.getGameBoard()[i][j] = 0;
            }
        }
        return board;
    }

    private Board fillBoardWithBombs(Board board){
        int bombNo = 0;
        do {
            int randomX = ThreadLocalRandom.current().nextInt(0, Globals.BOARD_DIMENSION);
            int randomY = ThreadLocalRandom.current().nextInt(0, Globals.BOARD_DIMENSION);
            if (board.getGameBoard()[randomX][randomY] != Globals.BOMB_VALUE){
                board.getGameBoard()[randomX][randomY] = Globals.BOMB_VALUE;
                bombNo++;
            }
        }
        while (bombNo < Globals.TOTAL_BOMBS_NUMBER);
        return board;
    }

    private Board fillBoardWithNumbers(Board board){
        for (int row=0; row<Globals.BOARD_DIMENSION; row++) {
            for (int col=0; col<Globals.BOARD_DIMENSION; col++) {
                if (board.getGameBoard()[row][col]!=Globals.BOMB_VALUE){
                    int bombsCounter = cellService.checkAdjacentCells(row, col, board);
                    board.getGameBoard()[row][col] = bombsCounter;
                }
            }
        }
        return board;
    }


    private Board showBoard(Board board){
        for (int i=0; i<Globals.BOARD_DIMENSION; i++) {
            for (int j=0; j<Globals.BOARD_DIMENSION; j++) {
                if (board.getGameBoard()[i][j]==Globals.BOMB_VALUE){
                    System.out.print("X");
                }
                else {
                    System.out.print(board.getGameBoard()[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
        return board;
    }


}
