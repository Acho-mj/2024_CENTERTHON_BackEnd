package likelion12th.centerthon.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import likelion12th.centerthon.service.gpt.GptService;
import likelion12th.centerthon.service.gpt.SttService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GptController {
    private final GptService gptService;
    private final SttService sttService;
    @PostMapping("/translate")
    public ResponseEntity<?> getAssistantMsg(@RequestParam("question") String question) {
        try {
            ResponseEntity<?> answer = gptService.getAssistantMsg(question);
            gptService.saveQnaHist(question, answer.getBody().toString());

            return answer;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }

    @PostMapping("/translate/stt")
    public ResponseEntity<?> getAssistantMsg(@RequestPart(value = "file") MultipartFile audioFile) {
        try {
            String question = sttService.recognizeSpeech(audioFile);
            ResponseEntity<?> answer = gptService.getAssistantMsg(question);
            Pattern pattern = Pattern.compile("\"text\":\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(question);
            String extractedQuestion = "";
            if (matcher.find()) {
                extractedQuestion = matcher.group(1);
            }
            gptService.saveQnaHist(extractedQuestion, answer.getBody().toString());
            return answer;
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error recognizing speech: " + e.getMessage());
        }
    }

    @GetMapping("/recommend")
    public ResponseEntity<?> getRecommendKeyword() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(gptService.getRecommendKeyword());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error recognizing speech: " + e.getMessage());
        }
    }
}