package com.b303.mokkozi.board.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("BoardWritePostRequest")
public class BoardWritePostReq {

    @ApiModelProperty(name = "게시글 content", example = "게시글 내용")
    private String content;

}
