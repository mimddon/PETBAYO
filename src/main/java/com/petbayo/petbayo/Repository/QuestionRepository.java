package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}