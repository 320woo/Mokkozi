package com.b303.mokkozi.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDto {

    private Long id;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date regDate;

    @Builder
    public CommentDto(Long id, String content, Date regDate) {
        this.id = id;
        this.content = content;
        this.regDate = regDate;
    }

}
