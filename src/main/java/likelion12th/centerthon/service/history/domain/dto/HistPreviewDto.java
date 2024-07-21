package likelion12th.centerthon.service.history.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class HistPreviewDto {
    private String questionHist;

    public HistPreviewDto(String questionHist) {
        this.questionHist = questionHist;
    }
}
