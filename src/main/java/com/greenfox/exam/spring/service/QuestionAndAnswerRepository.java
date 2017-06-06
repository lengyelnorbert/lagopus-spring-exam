package com.greenfox.exam.spring.service;


import com.greenfox.exam.spring.model.QuestionAndAnswer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface QuestionAndAnswerRepository extends CrudRepository<QuestionAndAnswer, Long> {

  List<QuestionAndAnswer> findAll();

}
