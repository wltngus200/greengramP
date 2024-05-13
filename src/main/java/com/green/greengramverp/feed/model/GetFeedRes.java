package com.green.greengramverp.feed.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GetFeedRes {
    private long feedId;
    private long writerId;
    private String nm;
    private List<String> pic;
    private String contents;
    private String location;
    private String createdAt;
}
