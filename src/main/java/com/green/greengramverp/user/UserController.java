package com.green.greengramverp.user;

import com.green.greengramverp.common.ResultDto;
import com.green.greengramverp.common.SignInRes;
import com.green.greengramverp.user.model.UserLogInReq;
import com.green.greengramverp.user.model.UserPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("sign-up")
    public ResultDto<Integer> userPost(@RequestPart(required = false) MultipartFile pic, @RequestPart UserPostReq p){
        int result= service.userPost(pic, p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("성공적으로 가입했습니다.")
                .resultData(result)
                .build();
    }

    @PostMapping("log-in")
    public ResultDto<SignInRes> userLogIn(@RequestBody UserLogInReq p){
        SignInRes result=service.userLogIn(p);
        return ResultDto.<SignInRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("성공적으로 로그인했습니다.")
                .resultData(result)
                .build();
    }

}
