package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> list() {
        return questionRepository.list();
    }

    @Override
    public void add(Question question) {
        questionRepository.add(question);
    }

    @Override
    public Question item(int qsId) {
        return questionRepository.item(qsId);
    }

    @Override
    public void update(Question question) {
        questionRepository.update(question);
    }

    @Override
    public void delete(int qsId) {
        questionRepository.delete(qsId);
    }
}