package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> list();

    void add(Question question);

    Question item(int qsId);

    void update(Question question);

    void delete(int qsId);
}
