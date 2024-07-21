package likelion12th.centerthon.service.info.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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
    @ElementCollection
    private List<String> description;
    // 예문
    @ElementCollection
    private List<String> exsentence;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Setter
    private Long viewCount = 0L;

    public Info(String word, List<String> description, List<String> exsentence) {
        this.word = word;
        this.description = description;
        this.exsentence = exsentence;
    }

    // 설명 업데이트 메서드
    public void updateDescription(List<String> descriptions) {
        this.description = descriptions;
    }

    // 예문 업데이트 메서드
    public void updateExsentence(List<String> exsentences) {
        this.exsentence = exsentences;
    }

    // 조회수 카운트
    public void incrementViewCount() {
        this.viewCount++;
    }

}
