package com.green.greengramverp.feed.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@ToString
public class PostFeedPicsDto {
    private long feedId;
    @Builder.Default
    private List<String> picsName=new ArrayList();
}
