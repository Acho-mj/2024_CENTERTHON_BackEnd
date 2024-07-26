package likelion12th.centerthon.service.info;


import likelion12th.centerthon.exception.WordExistsException;
import likelion12th.centerthon.service.info.domain.Info;
import likelion12th.centerthon.service.info.domain.dto.InfoDetailDto;
import likelion12th.centerthon.service.info.domain.dto.InfoPreviewDto;
import likelion12th.centerthon.service.info.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class InfoService {

    private final InfoRepository infoRepository;


    // 사용자 용어,설명,예문 작성
    public void createInfo(Info info) throws WordExistsException {
        Info existingInfo = infoRepository.findByWord(info.getWord());
        if(existingInfo != null){
            throw new WordExistsException("이미 등록된 단어입니다.");
        }else{
            // 새로운 단어 생성
            Info newInfo = new Info(info.getWord(), info.getDescription(), info.getExsentence());
            infoRepository.save(newInfo);
        }
    }

    // 사용자 설명, 예문 수정
    public void updateInfo(Long infoId, Info info) throws Exception{
        Info existingInfo = infoRepository.findById(infoId)
            .orElseThrow(() -> new IllegalArgumentException("해당 ID의 정보 없음"));

        existingInfo.updateDescription(info.getDescription());
        existingInfo.updateExsentence(info.getExsentence());

        infoRepository.save(existingInfo);
    }

    /*
    // 등록된 모든 용어 미리보기
    public List<InfoPreviewDto> getAllInfoPreviews() {
        return infoRepository.findAll().stream()
                .map(info -> new InfoPreviewDto(info.getWord(), info.getDescription()))
                .collect(Collectors.toList());
    }
    */

    // 최신순 전체 조회
    public List<InfoDetailDto> getInfoPreviewsNewest() {
        return infoRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(info -> new InfoDetailDto(info.getWord(), info.getDescription(), info.getExsentence()))
                .collect(Collectors.toList());
    }


    // 조회순 전체 조회
    public List<InfoDetailDto> getInfoPreviewsCount() {
        return infoRepository.findAllByOrderByViewCountDesc().stream()
                .map(info -> new InfoDetailDto(info.getWord(), info.getDescription(), info.getExsentence()))
                .collect(Collectors.toList());
    }

    // 용어 상세 조회하기
    public InfoDetailDto getInfoDetail(Long infoId){
        Info existingInfo = infoRepository.findById(infoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 정보 없음"));

        // 조회수 증가
        existingInfo.incrementViewCount();
        infoRepository.save(existingInfo);

        return new InfoDetailDto(existingInfo.getWord(), existingInfo.getDescription(), existingInfo.getExsentence());
    }


    // 검색하기
    public List<InfoPreviewDto> searchInfo(String keyword) {
        // word 검색
        List<Info> infos = infoRepository.findByWordContainingIgnoreCase(keyword);

        // 검색 결과가 미리보기 데이터로 출력
        List<InfoPreviewDto> result = infos.stream()
                .map(info -> new InfoPreviewDto(info.getWord(), info.getDescription()))
                .collect(Collectors.toList());

        return result;
    }

}
