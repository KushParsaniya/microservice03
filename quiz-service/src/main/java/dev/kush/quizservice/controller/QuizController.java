package dev.kush.quizservice.controller;

import dev.kush.quizservice.models.QuestionWrapper;
import dev.kush.quizservice.models.Quiz;
import dev.kush.quizservice.models.QuizDao;
import dev.kush.quizservice.models.UserAnswer;
import dev.kush.quizservice.services.QuizService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public Quiz createQuiz(@RequestBody QuizDao quizDao){
        return quizService.createQuiz(quizDao);
    }

    @GetMapping
    public List<Quiz> getAllQuiz(){
        return quizService.getAllQuiz();
    }

    @GetMapping("/{quizId}")
    public List<QuestionWrapper> getQuiz(@PathVariable Long quizId){
        return quizService.getQuiz(quizId);
    }

    @PostMapping("/submit/{quizId}")
    private Long submitQuiz(@PathVariable Long quizId , @RequestBody List<UserAnswer> userAnswers){
        return quizService.submitQuiz(quizId,userAnswers);
    }
}
