package com.petbayo.petbayo.Repository;

import com.petbayo.petbayo.Model.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Question> list() {
        return entityManager.createQuery("SELECT q FROM Question q", Question.class).getResultList();
    }

    @Override
    public void add(Question question) {
        entityManager.persist(question);
    }

    @Override
    public Question item(int qsId) {
        return entityManager.find(Question.class, qsId);
    }

    @Override
    public void update(Question question) {
        entityManager.merge(question);
    }

    @Override
    public void delete(int qsId) {
        Question question = entityManager.find(Question.class, qsId);
        entityManager.remove(question);
    }
}
