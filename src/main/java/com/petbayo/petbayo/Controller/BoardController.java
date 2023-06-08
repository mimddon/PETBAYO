package com.petbayo.petbayo.Controller;

import com.petbayo.petbayo.Model.Board;
import com.petbayo.petbayo.Model.Comment;
import com.petbayo.petbayo.Service.BoardService;
import com.petbayo.petbayo.Service.CommentService;
import com.petbayo.petbayo.pager.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }


    @GetMapping("/list")
    public String listPage(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;
        Pager pager = new Pager(page, pageSize);
        List<Board> boardList = boardService.list(pager);
        model.addAttribute("boardList", boardList);
        model.addAttribute("currentPage", page);

        int totalBoardCount = boardService.getTotalBoardCount();
        int totalPages = (int) Math.ceil((double) totalBoardCount / pageSize);
        model.addAttribute("totalPages", totalPages);

        return "board/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("board", new Board());
        return "board/add";
    }

    @PostMapping("/add")
    public String post(@ModelAttribute("board") Board board) {
        boardService.add(board);
        return "redirect:/board/list";
    }

    @GetMapping("/update/{qsId}")
    public String update(@PathVariable int qsId, Model model) {
        Board board = boardService.getBoardById(qsId);
        model.addAttribute("board", board);
        return "board/update";
    }

    @PostMapping("/update/{qsId}")
    public String update(@PathVariable int qsId, @ModelAttribute("board") Board board) {
        board.setQsId(qsId);
        boardService.update(board);

        return "redirect:/board/list";
    }

    @GetMapping("/detail/{qsId}")
    public String detail(@PathVariable int qsId, Model model) {
        Board board = boardService.getBoardById(qsId);
        board.setViewCnt(board.getViewCnt() + 1);
        boardService.update(board);
        model.addAttribute("board", board);

        return "/board/detail";
    }
    @GetMapping("delete/{qsId}")
    public String delete(@PathVariable int qsId){
        boardService.delete(qsId);
        return "redirect:../list";
    }
    @PostMapping("/comment/create")
    public String createComment(Comment comment) {
        Comment createdComment = commentService.addComment(comment);
        return "redirect:/board/detail/" + createdComment.getBoard().getQsId();
    }


}
