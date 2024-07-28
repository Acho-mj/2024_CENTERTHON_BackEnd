package likelion12th.centerthon.service.history.domain.dto;

import lombok.Getter;


@Getter
public class HistPreviewDto {
    private Long id;
    private String questionHist;

    public HistPreviewDto(Long id, String questionHist) {
        this.id = id;
        this.questionHist = questionHist;
    }
}
