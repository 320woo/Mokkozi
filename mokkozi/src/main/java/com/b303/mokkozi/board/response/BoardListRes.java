package com.b303.mokkozi.board.response;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.common.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@ApiModel("BoardListResponse")
public class BoardListRes extends BaseResponseBody {

    private Page<BoardDto> boardList;

    public static BoardListRes of(Integer statusCode, String message, Page<BoardDto> boardList) {
        BoardListRes res = new BoardListRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setBoardList(boardList);
        return res;
    }

}
