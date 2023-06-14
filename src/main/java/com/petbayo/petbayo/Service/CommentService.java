package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    Comment addComment(Comment comment);
}
