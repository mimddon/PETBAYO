package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Board;
import com.petbayo.petbayo.Repository.BoardRepository;
import com.petbayo.petbayo.pager.Pager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<Board> list(Pager pager) {
        return boardRepository.findAll();
    }

    @Override
    public void add(Board board) {
        board.setCreatedDate();
        boardRepository.save(board);
    }

    @Override
    public Board getBoardById(int qsId) {
        return boardRepository.findById(qsId).orElse(null);
    }

    @Override
    public void update(Board board) {
        Board existingBoard = boardRepository.findById(board.getQsId()).orElse(null);
        if (existingBoard != null) {
            existingBoard.update(board);
            boardRepository.save(existingBoard);
        }
    }
}
