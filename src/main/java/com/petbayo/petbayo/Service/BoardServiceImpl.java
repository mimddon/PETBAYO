package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Board;
import com.petbayo.petbayo.Repository.BoardRepository;
import com.petbayo.petbayo.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<Board> list(Pager pager) {
        return boardRepository.findAll();
    }

    @Override
    public void add(Board item) {
        item.setCreatedDate(new Date());
        boardRepository.save(item);
    }

    @Override
    public Board getBoardById(int qsId) {
        return boardRepository.findById(qsId).orElse(null);
    }

    @Override
    public void update(Board item) {
        Board existingBoard = boardRepository.findById(item.getQsId()).orElse(null);
        if (existingBoard != null) {
            existingBoard.update(item);
            boardRepository.save(existingBoard);
        }
    }
}
