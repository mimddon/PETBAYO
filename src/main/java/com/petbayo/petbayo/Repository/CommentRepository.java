package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
