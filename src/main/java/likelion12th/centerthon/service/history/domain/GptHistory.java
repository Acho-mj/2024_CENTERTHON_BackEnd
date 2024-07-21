package likelion12th.centerthon.service.history.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
public class GptHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionHist;
    private String answerHist;

    public GptHistory saveHist(String question, String answer) {
        this.questionHist = question;
        this.answerHist = answer;

        return this;
    }
}
