package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findByQsId(int qsId);
}
