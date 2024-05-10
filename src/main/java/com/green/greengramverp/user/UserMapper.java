package com.green.greengramverp.user;

import com.green.greengramverp.common.SignInRes;
import com.green.greengramverp.common.User;
import com.green.greengramverp.user.model.UserLogInReq;
import com.green.greengramverp.user.model.UserPostReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface UserMapper {
    int userPost(UserPostReq p);
    SignInRes userLogIn(UserLogInReq p);
    User idCheck(String uid);//아이디 체크 하고 로그인 작업

}
