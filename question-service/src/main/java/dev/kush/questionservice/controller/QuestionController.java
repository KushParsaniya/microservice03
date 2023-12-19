package dev.kush.questionservice.controller;

import dev.kush.questionservice.models.Question;
import dev.kush.questionservice.models.UserAnswer;
import dev.kush.questionservice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{questionId}")
    public Question getQuestion(@PathVariable Long questionId) {
        return questionService.getQuestion(questionId);
    }

    @PostMapping("create")
    public Question createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }

    @DeleteMapping("delete/{questionId}")
    public Question deleteQuestion(@PathVariable Long questionId){
        return questionService.deleteQuestion(questionId);
    }

    @GetMapping("/question-ids")
    public List<Question> getQuestionsByIds(@RequestParam List<Long> questionIds){
        return questionService.getQuestionsByIds(questionIds);
    }

    @PostMapping("/calculate-result")
    public Long calculateResult(@RequestBody List<UserAnswer> userAnswers){
        return questionService.calculateResult(userAnswers);
    }

    @GetMapping("/random-question-id")
    public List<Long> getRandomQuestionIdsByType(
            @RequestParam String type,
            @RequestParam Integer numOfQuestions
    ) {
        return questionService.getRandomQuestionIdsByType(type, numOfQuestions);
    }

}
