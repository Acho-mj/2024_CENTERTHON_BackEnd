package likelion12th.centerthon.service.gpt.repository;

import likelion12th.centerthon.service.gpt.domain.GptHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GptRepository extends JpaRepository<GptHistory, Long> {
}
