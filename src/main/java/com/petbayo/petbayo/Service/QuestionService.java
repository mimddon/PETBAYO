package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> list();

    void add(Question question);

    Question item(Long qsId);

    void update(Question question);

    void delete(Long qsId);
}