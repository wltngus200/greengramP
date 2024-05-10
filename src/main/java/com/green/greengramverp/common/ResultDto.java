package com.green.greengramverp.common;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data//
public class ResultDto<T> {
    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;
}
