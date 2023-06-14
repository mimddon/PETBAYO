package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Comment;
import com.petbayo.petbayo.Repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
