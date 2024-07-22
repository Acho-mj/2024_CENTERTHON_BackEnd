package likelion12th.centerthon.service.info.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InfoDetailDto {
    private String word;

    private List<String> description;
    private List<String> exsentence;

    public InfoDetailDto(String word, List<String> description, List<String> exsentence) {
        this.word = word;
        this.description = description;
        this.exsentence = exsentence;
    }
}
