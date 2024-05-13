package com.green.greengramverp.feed;

import com.green.greengramverp.common.ResultDto;
import com.green.greengramverp.feed.model.GetFeedRes;
import com.green.greengramverp.feed.model.PostFeedRes;
import com.green.greengramverp.feed.model.PostFeedReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService service;

    @PostMapping
    public ResultDto<PostFeedRes> postFeed(@RequestPart List<MultipartFile> pics, @RequestPart PostFeedReq p){
        PostFeedRes result= service.postFeedPics(pics, p);

        return ResultDto.<PostFeedRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("성공적으로 포스팅했습니다.")
                .resultData(result)
                .build();
    }
    @GetMapping
    public ResultDto<List<GetFeedRes>> getFeed(){
        List<GetFeedRes> result=service.getFeed();
        return ResultDto.<List<GetFeedRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result)
                .build();
    }
}
