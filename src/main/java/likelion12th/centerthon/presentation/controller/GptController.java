package likelion12th.centerthon.presentation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import likelion12th.centerthon.service.gpt.GptService;
import likelion12th.centerthon.service.gpt.repository.GptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequiredArgsConstructor
//public class GptController {
//
//    private final GptService gptService;
//
//    @PostMapping("/api/translate")
//    public ResponseEntity translate(@RequestBody GptReqDto gptReqDto) {
//        try {
//            GptResDto gptResDto = gptService.getChatRes(gptReqDto);
//
//            // qna 데이터 저장
//            gptService.saveQnaHist(gptReqDto, gptResDto);
//
//            return new ResponseEntity(gptResDto, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gpt")
public class GptController {
    private final GptService gptService;

    @PostMapping("/")
    public ResponseEntity<?> getAssistantMsg(@RequestParam String question) throws JsonProcessingException {
        ResponseEntity<?> answer = gptService.getAssistantMsg(question);
        gptService.saveQnaHist(question, answer.getBody().toString());

        return answer;
    }
}