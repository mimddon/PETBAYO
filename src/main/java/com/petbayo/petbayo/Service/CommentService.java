package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    Comment addComment(Comment comment);


    List<Comment> list(int qsId);
}
