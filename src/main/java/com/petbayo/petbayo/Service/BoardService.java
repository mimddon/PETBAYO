package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Board;
import com.petbayo.petbayo.pager.Pager;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BoardService {
    List<Board> list(Pager pager);

    void add(Board item);

    Board getBoardById(int qsId);

    void update(Board item);
}
