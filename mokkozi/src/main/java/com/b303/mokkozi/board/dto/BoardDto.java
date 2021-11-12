package com.b303.mokkozi.board.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date regDate;
    private String active;
    private String userEmail;


    @Builder
    public BoardDto(Long id, String title, String content, Date regDate, String active, String userEmail) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.active = active;
        this.userEmail = userEmail;
    }
}
