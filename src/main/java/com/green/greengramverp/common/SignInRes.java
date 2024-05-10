package com.green.greengramverp.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter //왜 이렇게 해야하지??
public class SignInRes {
    private long userId;
    private String nm;
    private String pic;
}
