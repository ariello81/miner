package pl.ryzykowski.miner.service;


import org.springframework.stereotype.Service;
import pl.ryzykowski.miner.config.Globals;
import pl.ryzykowski.miner.data.Board;

@Service
public class CellService {

    public int checkAdjacentCells(int row, int col, Board board, int value){
        int bombsCounter = 0;
        for (int x=row-1; x<=row+1; x++) {
            for (int y=col-1; y<=col+1; y++) {
                if ((x!=row || y!=col) && checkValueInCell(x, y, board, value)){
                    bombsCounter++;
                }
            }
        }
        return bombsCounter;
    }

    public int checkSelfAndAdjacentCells(int row, int col, Board board, int value){
        int bombsCounter = 0;
        for (int x=row-1; x<=row+1; x++) {
            for (int y=col-1; y<=col+1; y++) {
                if (checkValueInCell(x, y, board, value)){
                    bombsCounter++;
                }
            }
        }
        return bombsCounter;
    }

    private boolean checkValueInCell(int x, int y, Board board, int value){
        if (x>=0 && y>=0 && x<Globals.BOARD_DIMENSION && y<Globals.BOARD_DIMENSION && board.getGameBoard()[x][y]== value){
            return true;
        }
        return false;
    }

}
