package com.b303.mokkozi.board.dto;

import com.b303.mokkozi.common.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@ApiModel("BoardListResponse")
public class BoardListDto extends BaseResponseBody {

    private Page<BoardDto> boardList;

    public static BoardListDto of(Integer statusCode, String message, Page<BoardDto> boardList) {
        BoardListDto res = new BoardListDto();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setBoardList(boardList);
        return res;
    }

}
