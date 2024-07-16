package likelion12th.centerthon.service.info.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InfoPreviewDto {
    private String word;
    private List<String> description;

    public InfoPreviewDto(String word, List<String> description) {
        this.word = word;
        this.description = description;
    }
}
