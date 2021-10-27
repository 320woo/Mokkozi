package com.b303.mokkozi.board.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("BoardListGetRequest")
public class BoardListGetReq {

    @ApiModelProperty(name = "페이지 Idx", example = "1")
    @ApiParam(value = "페이지 Idx", defaultValue = "1")
    private int page;

    @ApiModelProperty(name = "페이지 Size", example = "15")
    @ApiParam(value = "페이지 Size", defaultValue = "10")
    private int size;

}
