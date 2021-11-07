package com.b303.mokkozi.comment.response;

import com.b303.mokkozi.comment.dto.CommentDto;
import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CommentWritePostResponse")
public class CommentWritePostRes extends BaseResponseBody {

    @ApiModelProperty(name = "댓글")
    CommentDto comment;

    public static CommentWritePostRes of(Integer statusCode, String message, Comment comment) {
        CommentWritePostRes res = new CommentWritePostRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setComment(new CommentDto(comment.getId(), comment.getContent(), comment.getRegDate()));
        return res;
    }
}
