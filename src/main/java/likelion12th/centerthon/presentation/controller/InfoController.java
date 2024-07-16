package likelion12th.centerthon.presentation.controller;


import likelion12th.centerthon.exception.WordExistsException;
import likelion12th.centerthon.service.info.InfoService;
import likelion12th.centerthon.service.info.domain.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/info")
public class InfoController {
    private final InfoService infoService;

    // 사용자 용어,설명,예문 작성
    @PostMapping("/new")
    public ResponseEntity<String> createInfo(@RequestBody Info info) {
        try {
            infoService.createInfo(info);
            return ResponseEntity.ok(info.getWord() + " 등록 완료");
        }catch (WordExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 등록된 단어");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("정보 생성 실패");
        }
    }
}
