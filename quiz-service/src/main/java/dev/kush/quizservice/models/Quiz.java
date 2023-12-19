package dev.kush.quizservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "quiz")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id  @GeneratedValue(strategy = IDENTITY)
    private Long quizId;
    private String title;

    @ElementCollection
    private List<Long> questionIds;

    public Quiz(String title, List<Long> questionIds) {
        this.title = title;
        this.questionIds = questionIds;
    }
}
