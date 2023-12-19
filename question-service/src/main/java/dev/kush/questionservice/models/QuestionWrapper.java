package dev.kush.questionservice.models;

public record QuestionWrapper(
        Long questionId, String question,String option1,
        String option2,String option3,String option4
) {
}
