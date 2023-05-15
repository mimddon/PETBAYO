package com.petbayo.petbayo.Service;

import com.petbayo.petbayo.Model.Question;
import com.petbayo.petbayo.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> list() {
        return questionRepository.findAll();
    }

    @Override
    public void add(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Question item(Long qsId) {
        return questionRepository.findById(qsId).orElseThrow(()->new RuntimeException("찾을 수 없는 질문"));
    }

    @Override
    public void update(Question question) {
        Question findQ = questionRepository.findById(question.getQsId()).orElseThrow(() -> new RuntimeException());
        findQ.update(question);
    }

    @Override
    public void delete(Long qsId) {
        Question findQ = questionRepository.findById(qsId).orElseThrow(() -> new RuntimeException());
        questionRepository.delete(findQ);
    }
}