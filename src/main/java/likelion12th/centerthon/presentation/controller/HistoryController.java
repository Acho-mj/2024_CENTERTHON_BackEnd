package likelion12th.centerthon.presentation.controller;

import jakarta.persistence.EntityNotFoundException;
import likelion12th.centerthon.service.history.HistoryService;
import likelion12th.centerthon.service.history.domain.dto.HistDetailDto;
import likelion12th.centerthon.service.history.domain.dto.HistPreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    // 대화 기록 전체 조회
    @GetMapping
    public ResponseEntity<?> getAllGptHistory() {
        try {
            List<HistPreviewDto> histPreviewDtoList = historyService.getAllHistory();
            return ResponseEntity.ok(histPreviewDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // 대화 기록 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getHistDetail(@PathVariable("id") Long histId) {
        try {
            HistDetailDto histDetailDto = historyService.getHistDetail(histId);
            return ResponseEntity.ok(histDetailDto);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(e.getMessage());
        }
    }
}
