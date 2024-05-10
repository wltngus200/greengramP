package com.green.greengramverp.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostReq {
    @JsonIgnore
    private long userId;

    private String uid;
    private String upw;
    private String nm;

    //@JsonIgnore
    private String pic;
}
