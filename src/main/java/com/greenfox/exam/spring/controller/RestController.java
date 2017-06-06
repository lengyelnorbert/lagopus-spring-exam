package com.greenfox.exam.spring.controller;


import com.greenfox.exam.spring.model.AnswersToReceive;
import com.greenfox.exam.spring.model.QuestionAndAnswer;
import com.greenfox.exam.spring.model.QuestionsToSend;
import com.greenfox.exam.spring.service.QuestionAndAnswerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class RestController {

  @Autowired
  QuestionAndAnswerService questionAndAnswerService;

  @RequestMapping(value = "/questions", method = RequestMethod.GET)
  public QuestionsToSend getFiveRandomQuestions() {
    questionAndAnswerService.fillUpQAListIfEmpty();
    return questionAndAnswerService.getQuestionsToSend();
  }

  @RequestMapping(value = "/answers", method = RequestMethod.POST)
  public Object receivedAnswers(AnswersToReceive answersToReceive) {
    if (questionAndAnswerService.checkReceivedAnswerIfCorrect(answersToReceive)) {
      return questionAndAnswerService.allAnswersCorrectGetTheProjectList();
    }
//    return questionAndAnswerService.returnBlankList();
    return questionAndAnswerService.allAnswersCorrectGetTheProjectList();
  }
}
