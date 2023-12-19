package dev.kush.quizservice.services.impl;

import dev.kush.quizservice.exceptions.ResourceNotFoundException;
import dev.kush.quizservice.feigns.QuestionFeign;
import dev.kush.quizservice.models.*;
import dev.kush.quizservice.repository.QuizRepository;
import dev.kush.quizservice.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionFeign questionFeign;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, QuestionFeign questionFeign) {
        this.quizRepository = quizRepository;
        this.questionFeign = questionFeign;
    }

    @Override
    public Quiz createQuiz(QuizDao quizDao) {
        List<Long> questionsIds = questionFeign.getRandomQuestionIdsByType(quizDao.type(), quizDao.numOfQuestions());
        
        if (questionsIds == null || questionsIds.isEmpty()) {
            throw new IllegalArgumentException("No questions for this type.");
        }
        
        Quiz quiz = new Quiz(quizDao.title(),questionsIds);
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuiz() {
        // TODO: Get All Quiz With Questions
        return quizRepository.findAll();
    }

    @Override
    public List<QuestionWrapper> getQuiz(Long quizId) {
        List<Question> questions = getQuestionsByQuizId(quizId);

        return questions.stream().map(question -> new QuestionWrapper(
                question.questionId(), question.question(), question.option1(),
                question.option2(), question.option3(), question.option4()
        )).toList();

    }


    @Override
    public Long submitQuiz(Long quizId, List<UserAnswer> userAnswers) {

        if(userAnswers == null || userAnswers.isEmpty()) {
            return 0L;
        }

        List<Question> questions = getQuestionsByQuizId(quizId);

        questions.sort(Comparator.comparingLong(Question::questionId));
        userAnswers.sort(Comparator.comparingLong(UserAnswer::questionId));

        return IntStream.range(0, questions.size()).
                filter(i -> questions.get(i).answer().equals(userAnswers.get(i).userAnswer()))
                .count();

    }


    private List<Question> getQuestionsByQuizId(Long quizId) {

        // first find quiz
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("No such quiz exists.")
        );
        // find list of questionIds
        List<Long> questionIds = quiz.getQuestionIds();

        // call question-service
        return questionFeign.getQuestionsByIds(questionIds);
    }
}
