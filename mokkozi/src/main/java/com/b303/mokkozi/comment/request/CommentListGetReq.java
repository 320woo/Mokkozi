package com.b303.mokkozi.comment.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("CommentListGetRequest")
public class CommentListGetReq {

    @ApiModelProperty(name = "게시글 Id", example = "1")
    @ApiParam(value = "게시글 Id", defaultValue = "1")
    private Long boardId;
}
