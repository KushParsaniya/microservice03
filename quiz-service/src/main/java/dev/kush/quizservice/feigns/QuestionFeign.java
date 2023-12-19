package dev.kush.quizservice.feigns;

import dev.kush.quizservice.models.Question;
import dev.kush.quizservice.models.QuestionWrapper;
import dev.kush.quizservice.models.UserAnswer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "question-service")
public interface QuestionFeign {
    @GetMapping("questions/question-ids")
    public List<Question> getQuestionsByIds(@RequestParam List<Long> questionIds);

    @PostMapping("questions/calculate-result")
    public Long calculateResult(@RequestBody List<UserAnswer> userAnswers);

    @GetMapping("questions/random-question-ids")
    public List<Long> getRandomQuestionIdsByType(
            @RequestParam String type,
            @RequestParam Integer numOfQuestions
    );
}
