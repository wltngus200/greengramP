package com.green.greengramverp.feed;

import com.green.greengramverp.feed.model.GetFeedRes;
import com.green.greengramverp.feed.model.PostFeedPicsDto;
import com.green.greengramverp.feed.model.PostFeedReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface FeedMapper {
    int postFeed(PostFeedReq p);
    int postFeedPics(PostFeedPicsDto p);
    List<GetFeedRes> getFeed();
    List<String> getFeedPic(long boardId);

}
