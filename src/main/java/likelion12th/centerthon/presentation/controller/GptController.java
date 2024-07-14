package likelion12th.centerthon.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import likelion12th.centerthon.service.gpt.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/gpt")
public class GptController {
    private final GptService gptService;

    @PostMapping("/translate")
    public ResponseEntity<?> getAssistantMsg(@RequestParam String question) throws JsonProcessingException {
        ResponseEntity<?> answer = gptService.getAssistantMsg(question);
        gptService.saveQnaHist(question, answer.getBody().toString());

        return answer;
    }
}