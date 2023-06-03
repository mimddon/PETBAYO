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

    @GetMapping("/board/listPage")
    public String listPage(Model model) {
        Pager pager = new Pager(1, 10);  // Pager 객체 생성 및 초기화
        List<Board> boardList = boardService.list(pager);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("item", new Board());
        return "board/add";
    }

    @PostMapping("/add")
    public String post(@ModelAttribute("item") Board item) {
        boardService.add(item);
        return "redirect:/board/list";
    }

    @GetMapping("/board/update/{qsId}")
    public String update(@PathVariable int qsId, Model model) {
        Board board = boardService.getBoardById(qsId);
        model.addAttribute("board", board);
        return "board/update";
    }

    @PostMapping("/board/update/{qsId}")
    public String update(@PathVariable int qsId, Board item) {
        item.setQsId(qsId);
        boardService.update(item);
        return "redirect:/board/list";
    }
}