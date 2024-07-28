package likelion12th.centerthon.service.history;

import jakarta.persistence.EntityNotFoundException;
import likelion12th.centerthon.service.history.domain.GptHistory;
import likelion12th.centerthon.service.history.domain.dto.HistDetailDto;
import likelion12th.centerthon.service.history.domain.dto.HistPreviewDto;
import likelion12th.centerthon.service.history.repository.HistoryRepository;
import likelion12th.centerthon.service.info.domain.dto.InfoPreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    // 대화 기록 전체 조회
    public List<HistPreviewDto> getAllHistory() {
        return historyRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(hist -> new HistPreviewDto(hist.getId(), hist.getQuestionHist()))
                .collect(Collectors.toList());
    }

    // 대화 기록 상세 조회
    public HistDetailDto getHistDetail(Long HistId) {
        GptHistory gptHistory = historyRepository.findById(HistId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 정보 없음"));

        return new HistDetailDto(gptHistory.getQuestionHist(), gptHistory.getAnswerHist());
    }
}
