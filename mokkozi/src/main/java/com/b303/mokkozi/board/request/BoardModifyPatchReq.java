package com.b303.mokkozi.board.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardModifyPatchRequest")
public class BoardModifyPatchReq {

    @ApiModelProperty(name="게시글 PK",example = "1")
    private Long id;
    @ApiModelProperty(name = "게시글 content", example = "게시글 내용")
    private String content;


}
