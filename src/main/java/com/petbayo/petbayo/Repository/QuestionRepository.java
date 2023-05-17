package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByUserIdOrDisclosure(String loggedInUsername, Question.Disclosure disclosure);

    List<Question> findByDisclosure(Question.Disclosure disclosure);
}