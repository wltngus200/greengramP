package com.green.greengramverp.user;

import com.green.greengramverp.common.CustomFileUtils;
import com.green.greengramverp.common.SignInRes;
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
        String fileName = customFileUtils.makeRandomFileName(pic);//customFileUtils에서 3단계를 걸쳐 랜덤 이름 생성
        p.setPic(fileName);//랜덤이름을 만들고 세팅
        int result = mapper.userPost(p);

        if (pic == null || pic.isEmpty()) {return result;}

        //사진 관리
        try {
            String path = String.format("user/%d", p.getUserId());
            customFileUtils.makeFolder(path);
            String target = path + "/" + fileName;
            customFileUtils.transferTo(pic, target);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("에러!");
        }
        return result;
    }

    public SignInRes userLogIn(UserLogInReq p){
        return mapper.userLogIn(p);
    }
}
