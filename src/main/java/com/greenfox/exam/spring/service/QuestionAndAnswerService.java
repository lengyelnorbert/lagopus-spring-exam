package com.greenfox.exam.spring.service;


import com.greenfox.exam.spring.model.QuestionAndAnswer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionAndAnswerService {

  @Autowired
  QuestionAndAnswerRepository questionAndAnswerRepository;

  public void fillUpQAListIfEmpty() {
    if (questionAndAnswerRepository.findAll().size() == 0) {
      fillUpQAList();
    }
  }

  public void fillUpQAList() {
    questionAndAnswerRepository
            .save(new QuestionAndAnswer("What is the color code of Green Fox?", "#3CB879"));
    questionAndAnswerRepository
            .save(new QuestionAndAnswer("When was Green Fox founded?(yyyy.mm.)", "2015.09."));
    questionAndAnswerRepository
            .save(new QuestionAndAnswer("When did your course start?", "2017.03.13"));
    questionAndAnswerRepository
            .save(new QuestionAndAnswer("What type of dog Barbi has?", "Whippet"));
    questionAndAnswerRepository
            .save(new QuestionAndAnswer("What is HerrNorbert's favourite color?", "Green"));
    questionAndAnswerRepository.save(new QuestionAndAnswer(
            "How many classes are learning at Green Fox Academy at this moment?", "4"));
    questionAndAnswerRepository
            .save(new QuestionAndAnswer("How many mentors teach at Green Fox at this moment?",
                    "16"));
    questionAndAnswerRepository
            .save(new QuestionAndAnswer("What was the name of the first Green Fox class?",
                    "Vulpes"));
    questionAndAnswerRepository
            .save(new QuestionAndAnswer("How many likes do we have on facebook? ", "~3300"));
    questionAndAnswerRepository.save(new QuestionAndAnswer("What is Tojas's horoscope?", "Libra"));
  }

  public List<QuestionAndAnswer> getFiveRandomQuestions(){
    return questionAndAnswerRepository.findAll();
  }
}
