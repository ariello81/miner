package pl.ryzykowski.miner.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ryzykowski.miner.data.Board;
import pl.ryzykowski.miner.service.BoardService;

@RestController
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    @GetMapping("/clear")
    public int[][] clear(){
        return boardService.clearBoard().getGameBoard();
    }

    @GetMapping("/create")
    public int[][] create(@RequestParam int x, @RequestParam int y){
        return boardService.createBoard(x, y);
    }




}
