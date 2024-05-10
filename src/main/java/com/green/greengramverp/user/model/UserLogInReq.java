package com.green.greengramverp.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogInReq {
    private String uid;
    private String upw;
}
