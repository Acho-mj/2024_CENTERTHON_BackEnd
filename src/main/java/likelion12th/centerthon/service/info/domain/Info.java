package likelion12th.centerthon.service.info.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 용어(단어)
    private String word;
    // 설명(뜻)
    private String description;
    // 예문
    private String exsentence;

    public Info(String word, String description, String exsentence) {
        this.word = word;
        this.description = description;
        this.exsentence = exsentence;
    }

}
