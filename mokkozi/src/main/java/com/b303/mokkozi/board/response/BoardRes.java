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
@ApiModel("BoardResponse")
public class BoardRes extends BaseResponseBody {
    @ApiModelProperty(name = "게시글")
    BoardDto board;

    public static BoardRes of(Integer statusCode, String message, Board board) {
        BoardRes res = new BoardRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setBoard(new BoardDto(board.getId(),board.getTitle(),board.getContent(),board.getRegDate(),board.getActive(),board.getUser().getEmail()));
        return res;
    }
}
