package com.green.greengramverp.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFeedReq {
    @JsonIgnore
    private long feedId; //사진 올릴 때 사용

    private long writerId;
    private String contents;
    private String location;
}
