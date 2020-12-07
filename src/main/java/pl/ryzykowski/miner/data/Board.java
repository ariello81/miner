package pl.ryzykowski.miner.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.ryzykowski.miner.config.Globals;

@Component
@Getter
@Setter
public class Board {

    private int[][] gameBoard = new int[Globals.BOARD_DIMENSION][Globals.BOARD_DIMENSION];

}
