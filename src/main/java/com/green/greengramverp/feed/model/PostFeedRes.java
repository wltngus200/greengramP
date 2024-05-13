package com.green.greengramverp.feed.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostFeedRes {
    private long feedId;
    private List<String> pics; //사진 이름들을 저장하는 리스트
    //Builder 없이 service에서 객체 생성을 해도 됨
}
