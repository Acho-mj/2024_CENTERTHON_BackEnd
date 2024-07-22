package likelion12th.centerthon.service.info.repository;

import likelion12th.centerthon.service.info.domain.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {
    // 단어 등록 시 중복 단어 확인
    Info findByWord(String word);
    List<Info> findByWordContainingIgnoreCase(String keyword);

    // 최신순으로 정렬
    List<Info> findAllByOrderByCreatedAtDesc();

    // 조회수 순으로 정렬
    List<Info> findAllByOrderByViewCountDesc();
}
