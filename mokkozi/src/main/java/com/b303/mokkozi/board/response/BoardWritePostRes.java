package com.b303.mokkozi.board.response;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardWritePostResponse")
public class BoardWritePostRes extends BaseResponseBody {

    @ApiModelProperty(name = "게시글")
    BoardDto board;

    public static BoardWritePostRes of(Integer statusCode, String message, Board board) {
        BoardWritePostRes res = new BoardWritePostRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setBoard(new BoardDto(board.getId(),board.getTitle(),board.getContent(),board.getRegDate(),board.getActive(),board.getUserId()));
        return res;
    }

}
