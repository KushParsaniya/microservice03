package dev.kush.questionservice.services;

import dev.kush.questionservice.models.Question;
import dev.kush.questionservice.models.UserAnswer;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question deleteQuestion(Long questionId);
    Question getQuestion(Long questionId);
    List<Question> getAllQuestions();

    // quiz-service will send questionsIds and we have to send them questions
    // this questionsIds are also received By findRandomQuestionsIdsByType
    List<Question> getQuestionsByIds(List<Long> questionIds);
    Long calculateResult(List<UserAnswer> userAnswer);
    List<Long> getRandomQuestionIdsByType(String type, Integer numOfQuestions);

    // TODO: Update question
}
