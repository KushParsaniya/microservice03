package dev.kush.questionservice.services.impl;

import dev.kush.questionservice.exceptions.ResourceNotFoundException;
import dev.kush.questionservice.models.Question;
import dev.kush.questionservice.models.UserAnswer;
import dev.kush.questionservice.repository.QuestionRepository;
import dev.kush.questionservice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question deleteQuestion(Long questionId) {
        if(questionId == null) {
            throw new IllegalArgumentException("Question cannot be null.");
        }

        Question question = getById(questionId);

        questionRepository.delete(question);
        return question;
    }


    @Override
    public Question getQuestion(Long questionId) {
        return getById(questionId);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionsByIds(List<Long> questionIds) {

        if (questionIds == null || questionIds.isEmpty()) {
            return List.of();
        }

        return questionIds.stream().map(this::getById).toList();
    }

    @Override
    public Long calculateResult(List<UserAnswer> userAnswer) {

        // if no userAnswer send then result is null
        if (userAnswer == null || userAnswer.isEmpty()) {
            return 0L;
        }

        List<Question> questions = userAnswer.
                stream().map(UserAnswer::questionId)
                .map(this::getById).toList();

         final long correctAnswer = IntStream.range(0, userAnswer.size())
                .filter(index -> userAnswer.get(index).userAnswer().equals(questions.get(index).getAnswer()))
                .count();

//         Second method:
//        int i = 0;
//        int correct = 0;
//        for (Question question : questions) {
//            if (Objects.equals(question.getAnswer(), userAnswer.get(i).userAnswer())){
//                correct++;
//            }
//            i++;
//        }
        return correctAnswer;
    }

    @Override
    public List<Long> getRandomQuestionIdsByType(String type, Integer numOfQuestions) {
        final List<Question> questions = questionRepository.findRandomQuestionsByType(type, numOfQuestions);

        return questions.stream().map(Question::getQuestionId).toList();
    }

    private Question getById(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(
                () -> new ResourceNotFoundException("Question not found.")
        );
    }
}
