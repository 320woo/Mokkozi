package com.b303.mokkozi.gallery.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;

@Getter
@Setter
public class GalleryPostReq {
    @ApiModelProperty(name = "S3에 올라간 파일 경로", example = "S3 업로드 URL")
    private String filePath;

    @ApiModelProperty(name = "원래 파일 이름", example = "Kakao1234.jpeg")
    private String title;

    @ApiModelProperty(name = "파일 유형. 사용자 프로필에 올라간 이미지인지, 게시글 첨부 파일인지 구분.", example = "프로필 or 게시글")
    private String sort;

    @Nullable
    @ApiModelProperty(name = "사용자 프로필 이미지인 경우 사용한다.", example = "3")
    private String userEmail;

    @Nullable
    @ApiModelProperty(name = "게시글 이미지인 경우 사용한다.", example = "50")
    private Long boardId;

}
