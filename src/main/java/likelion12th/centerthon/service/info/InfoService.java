package likelion12th.centerthon.service.info;


import likelion12th.centerthon.exception.WordExistsException;
import likelion12th.centerthon.service.info.domain.Info;
import likelion12th.centerthon.service.info.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
}
