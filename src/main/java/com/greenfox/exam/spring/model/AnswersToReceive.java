package com.greenfox.exam.spring.model;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AnswersToReceive {


  private long id;

  private List<Answer> answers;

  public AnswersToReceive() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }
}
