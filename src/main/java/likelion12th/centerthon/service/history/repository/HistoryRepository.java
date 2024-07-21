package likelion12th.centerthon.service.history.repository;

import likelion12th.centerthon.service.history.domain.GptHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<GptHistory, Long> {
}
