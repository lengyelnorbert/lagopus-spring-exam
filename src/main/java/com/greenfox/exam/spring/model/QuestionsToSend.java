package com.greenfox.exam.spring.model;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Component
public class QuestionsToSend {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private List<Question> questions;

  public QuestionsToSend() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }
}
