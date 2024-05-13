package com.green.greengramverp.feed;

import com.green.greengramverp.common.CustomFileUtils;
import com.green.greengramverp.feed.model.GetFeedRes;
import com.green.greengramverp.feed.model.PostFeedPicsDto;
import com.green.greengramverp.feed.model.PostFeedReq;
import com.green.greengramverp.feed.model.PostFeedRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedMapper mapper;
    private final CustomFileUtils customFileUtils;

    @Transactional
    PostFeedRes postFeedPics(List<MultipartFile> pics, PostFeedReq p){//List 멤버필드명=PostMan에 이름
        int result=mapper.postFeed(p); //feed에 올리고 영향받은 값

        PostFeedPicsDto dto= PostFeedPicsDto.builder().feedId(p.getFeedId()).build();
        //객체를 만들고 그 객체에 필드 초기화
        log.info("{}",dto);
        try{
            String path=String.format("feed/%d",p.getFeedId());
            customFileUtils.makeFolder(path); //void 메소드
            for(MultipartFile mf:pics){
                String randomName=customFileUtils.makeRandomFileName(mf);
                String target=String.format("%s/%s",path,randomName);
                customFileUtils.transferTo(mf, target);
                dto.getPicsName().add(randomName);//리스트를 불러와 .add(랜덤이름)
                log.info("{}",dto);
            }int picresult=mapper.postFeedPics(dto);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("UPROAD ERROR");
        }
        log.info("{}",dto);
        return PostFeedRes.builder()
                .feedId(dto.getFeedId())
                .pics(dto.getPicsName())
                .build();
    }
    List<GetFeedRes> getFeed(){
        List<GetFeedRes> list=mapper.getFeed();
        for(GetFeedRes res:list){
            List<String> pics=mapper.getFeedPic(res.getFeedId());
            res.setPic(pics);
        }
        return list;
    }
}
