package likelion12th.centerthon.presentation.controller;


import likelion12th.centerthon.exception.WordExistsException;
import likelion12th.centerthon.service.info.InfoService;
import likelion12th.centerthon.service.info.domain.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/info")
public class InfoController {
    private final InfoService infoService;

    // 사용자 용어,설명,예문 작성
    @PostMapping
    public ResponseEntity<String> createInfo(@RequestBody Info info) {
        try {
            infoService.createInfo(info);
            return ResponseEntity.ok(info.getWord() + " 등록 완료");
        }catch (WordExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("정보 생성 실패");
        }
    }

    // 사용자 설명, 예문 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateInfo(@PathVariable("id") Long infoId,
                                             @RequestBody Info info) {
        try {
            infoService.updateInfo(infoId, info);
            return ResponseEntity.ok(info.getWord() + " 수정 완료");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("정보 수정 실패");
        }
    }
}
