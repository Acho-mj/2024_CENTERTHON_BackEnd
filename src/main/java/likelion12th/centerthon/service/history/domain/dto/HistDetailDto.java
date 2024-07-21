package likelion12th.centerthon.service.history.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HistDetailDto {
    private String questionHist;
    private String answerHist;

    public HistDetailDto(String questionHist, String answerHist) {
        this.questionHist = questionHist;
        this.answerHist = answerHist;
    }
}
