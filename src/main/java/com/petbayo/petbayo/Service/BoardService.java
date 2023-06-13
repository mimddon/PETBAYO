package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Board;
import com.petbayo.petbayo.pager.Pager;

import java.util.List;

public interface BoardService {
    List<Board> list(Pager pager);

    void add(Board board);

    Board getBoardById(int qsId);

    void update(Board board);

    void delete(int qsId);

    int getTotalBoardCount();
}
