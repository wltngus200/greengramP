package com.green.greengramverp.user;

import com.green.greengramverp.common.CustomFileUtils;
import com.green.greengramverp.common.SignInRes;
import com.green.greengramverp.common.User;
import com.green.greengramverp.user.model.UserLogInReq;
import com.green.greengramverp.user.model.UserPostReq;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final CustomFileUtils customFileUtils; //내가 만들어야 함! //빈등록 후 가져오기

    @Transactional
    int userPost(MultipartFile pic, UserPostReq p) {
        // 패스워드 암호화
        String password = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
        p.setUpw(password);

        //랜덤 파일 이름
        String fileName = customFileUtils.makeRandomFileName(pic);//customFileUtils에서 3단계를 걸쳐 랜덤 이름 생성
        p.setPic(fileName);//랜덤이름을 만들고 세팅

        int result = mapper.userPost(p);//위의 2가지를 변경하여 DB에 저장

        if (pic == null || pic.isEmpty()) {return result;} //사진이 없는 경우

        //사진 관리
        try {
            String path = String.format("user/%d", p.getUserId());
            customFileUtils.makeFolder(path);
            String target = path + "/" + fileName;
            customFileUtils.transferTo(pic, target);//사진 pic을 target(추가경로+파일이름) 저장
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("에러!");
        }
        return result;
    }

    public SignInRes userLogIn(UserLogInReq p){
        User user=mapper.idCheck(p.getUid());
        if(user==null){throw new RuntimeException("일치하는 회원이 없습니다.");
        }else if(!BCrypt.checkpw(p.getUpw(),user.getUpw())){throw new RuntimeException("비밀번호가 다릅니다.");}
        else{ return mapper.userLogIn(p);}
    }
}
