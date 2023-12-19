package dev.kush.quizservice.services;

import dev.kush.quizservice.models.QuestionWrapper;
import dev.kush.quizservice.models.Quiz;
import dev.kush.quizservice.models.QuizDao;
import dev.kush.quizservice.models.UserAnswer;

import java.util.List;

public interface QuizService {

    Quiz createQuiz(QuizDao quizDao);
    List<Quiz> getAllQuiz();
    List<QuestionWrapper> getQuiz(Long quizId);
    Long submitQuiz(Long quizId, List<UserAnswer> userAnswers);
}
