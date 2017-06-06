package com.greenfox.exam.spring.service;


import com.greenfox.exam.spring.model.AnswersToReceive;
import com.greenfox.exam.spring.model.Question;
import com.greenfox.exam.spring.model.QuestionAndAnswer;
import com.greenfox.exam.spring.model.QuestionsToSend;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionAndAnswerService {

  private long questionToSendId = 0;
  QuestionsToSend questionsToSend;
  List<QuestionsToSend> sentQuestions = new ArrayList<>();
  boolean allAnswersAreCorrect;

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

  public QuestionsToSend getQuestionsToSend() {
    questionsToSend = new QuestionsToSend();
    List<Question> questions = new ArrayList<>();
    questionToSendId += 1;
    questionsToSend.setId(questionToSendId);
    List<Long> sentIdNumber = new ArrayList<>();
    int i = 0;
    while (i != 5) {
      Question randomQuestion = randomQuestion();
      if (!sentIdNumber.contains(randomQuestion.getId())) {
        questions.add(randomQuestion);
        sentIdNumber.add(randomQuestion.getId());
        i += 1;
      }
    }
    questionsToSend.setQuestions(questions);
    sentQuestions.add(questionsToSend);
    return questionsToSend;
  }

  private Question randomQuestion() {
    Random r = new Random();
    long randomId = r.nextInt(questionAndAnswerRepository.findAll().size()) + 1;
    QuestionAndAnswer randomQuestionAndAnswer = questionAndAnswerRepository.findOne(randomId);

    Question question = new Question();
    question.setId(randomQuestionAndAnswer.getId());
    question.setQuestion(randomQuestionAndAnswer.getQuestion());

    return question;

  }

  public boolean checkReceivedAnswerIfCorrect(AnswersToReceive answersToReceive) {
    for (QuestionsToSend questionToSend : sentQuestions) {
      if (questionToSend.getId() == answersToReceive.getId()) {
        checkAllAnswers(questionToSend, answersToReceive);
      }
    }
    return allAnswersAreCorrect;
  }

  private boolean checkAllAnswers(QuestionsToSend questionsToSend,
          AnswersToReceive answersToReceive) {
    allAnswersAreCorrect = true;
    for (int i = 0; i < 5; i++) {
      long questionId = questionsToSend.getQuestions().get(i).getId();
      String rightAnswerForTheQuestion = questionAndAnswerRepository.findOne(questionId)
              .getAnswer();
      if (!answersToReceive.getAnswers().get(i).getAnswer().equals(rightAnswerForTheQuestion)) {
        allAnswersAreCorrect = false;
      }
    }
    return allAnswersAreCorrect;
  }

  public List<String> returnBlankList() {
    List<String> blankList = new ArrayList<>();
    return blankList;
  }

  public static Object allAnswersCorrectGetTheProjectList() {
    HttpURLConnection connection = null;
    String urlParameters = "";

    try {
      URL url = new URL("https://springexamserver.herokuapp.com/projects/eagles");
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type",
              "application/x-www-form-urlencoded");

      connection.setRequestProperty("Content-Length",
              Integer.toString(urlParameters.getBytes().length));
      connection.setRequestProperty("Content-Language", "en-US");

      connection.setUseCaches(false);
      connection.setDoOutput(true);

      DataOutputStream wr = new DataOutputStream(
              connection.getOutputStream());
      wr.writeBytes(urlParameters);
      wr.close();

      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
      }
      rd.close();
      return response.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }
}
