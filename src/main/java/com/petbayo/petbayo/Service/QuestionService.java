package com.petbayo.petbayo.Service;

import java.util.List;

public interface QuestionService {
    List<Question> list();

    void add(Question question);

    Question item(int qsId);

    void update(Question question);

    void delete(int qsId);
}
