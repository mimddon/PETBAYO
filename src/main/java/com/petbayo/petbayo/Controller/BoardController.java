package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Board;
import com.petbayo.petbayo.Service.BoardService;
import com.petbayo.petbayo.pager.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String list(Pager pager, Model model) {
        List<Board> boardList = boardService.list(pager);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("board", new Board());
        return "board/add";
    }

    @PostMapping("/add")
    public String post(Board item) {
        boardService.add(item);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{qsId}")
    public String update(@PathVariable int qsId, Model model) {
        Board board = boardService.getBoardById(qsId);
        model.addAttribute("board", board);
        return "board/update";
    }

    @PostMapping("/update/{qsId}")
    public String update(@PathVariable int qsId, Board item) {
        item.setQsId(qsId);
        boardService.update(item);
        return "redirect:/board/list";
    }
}
