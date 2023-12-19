package dev.kush.questionservice.repository;

import dev.kush.questionservice.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(value = "SELECT * FROM questions q WHERE q.type= :questionType" +
            " ORDER BY RANDOM() LIMIT :numOfQuestions",nativeQuery = true)
    List<Question> findRandomQuestionsByType(@Param("questionType") String type,@Param("numOfQuestions") Integer numQ);
}
