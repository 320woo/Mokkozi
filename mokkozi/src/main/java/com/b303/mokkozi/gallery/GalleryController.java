package com.b303.mokkozi.gallery;

import com.b303.mokkozi.board.BoardService;
import com.b303.mokkozi.common.S3Uploader;
import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.gallery.request.GalleryPostReq;
import com.b303.mokkozi.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/meet/gallery")
public class GalleryController {
    @Autowired
    private S3Uploader s3Uploader;

    @Autowired
    GalleryService galleryService;

    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    /**
     * 게시글 이미지 혹은 사용자 프로필 이미지를 S3에 업로드한다.
     * @Param file, fileInfo
     * @Return Response Message
     */
    @PostMapping("/iamges")
    @ApiOperation(value = "이미지 파일 업로드", notes = "업로드한 이미지를 S3 서버에 올립니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "이미지 업로드 성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "파일 업로드 실패")})
    @Transactional
    public ResponseEntity<? extends BaseResponseBody> upload(
            @RequestBody(required = true) MultipartFile file, @RequestBody GalleryPostReq fileInfo) {
        // 1. S3에 먼저 업로드한다.
        String file_path = "";
        try {
            file_path = s3Uploader.uploadToS3(file, file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "파일 업로드 실패하였습니다.."));
        }

        // 2. S3에 업로드한 URL을 file_path로 하여 DB에 저장한다.
        if (!file_path.equals("")) {
            // 정상적으로 S3에 업로드한 URL을 받았으므로 이를 DB에 저장한다.
            fileInfo.setFilePath(file_path);

            // 사용자 프로필과 관련된 파일을 저장하는 경우.
            if (fileInfo.getUserEmail() != null) {
                // 단 사용자 프로필을 업로드하는 경우, 먼저 User 객체가 저장되어 있어야 한다.
                User user = userService.findByEmail(fileInfo.getUserEmail()).get();

                // Gallery 파일을 return 받는다.
                galleryService.userGalleryCreate(fileInfo, user);
            }
            // 게시글과 관련된 파일을 저장하는 경우.
            else if (fileInfo.getBoardId() != null) {
                // 단 게시글 이미지를 업로드하는 경우, 먼저 Board 객체가 저장되어 있어야 한다.
                Board board = boardService.getBoardDetail(fileInfo.getBoardId());
                galleryService.boardGalleryCreate(fileInfo, board);
            }
            // 둘 다 아닌 경우 Error 메시지 출력.
            else {
                return ResponseEntity.status(500).body(BaseResponseBody.of(500, "파일의 종류가 명시되어 있지 않습니다. email 또는 게시글 번호를 지정해 주세요."));
            }
            // 이 경우 S3에도 업로드가 되고, DB에도 저장이 된 경우이다.
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "파일 업로드에 성공하였습니다."));
        }
        // 이 경우는 file_path가 ""인 경우로, 정상적으로 S3에 업로드 되지 않았다는 뜻이다.
        return ResponseEntity.status(500).body(BaseResponseBody.of(500, "저장된 파일의 S3 URL이 Null입니다."));
    }


    /**
     * 사용자의 대표 프로필을 S3에 업로드하고, DB에 저장한다.
     * @Params file, userEmail
     * @Return Response Message
     */
    @PostMapping("/myProflie")
    @Transactional
    @ApiOperation(value = "사용자 대표 프로필 업로드", notes = "회원가입시 첨부한 대표 프로필을 S3에 올리고, User 객체의 profile 을 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "대표 프로필 업로드 성공"),
            @ApiResponse(code = 500, message = "대표 프로필 업로드 실패"),
            @ApiResponse(code = 401, message = "권한 인증 실패")})
    public ResponseEntity<? extends BaseResponseBody> uploadMyProfile(
            @RequestBody MultipartFile file,
            @RequestBody GalleryPostReq galleryPostReq) {

        String file_path = "";
        try {
            file_path = s3Uploader.uploadToS3(file, file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "대표 프로필 업로드 실패"));
        }

        if (!file_path.equals("")) {
            // User 객체의 profile 컬럼 속성을 업데이트한다.
            User user = userService.findByEmail(galleryPostReq.getUserEmail()).get();
            user.setProfile(file_path);
            userService.userUpdate(user);
        }
        else {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "대표 프로필 업로드 실패. file_path Null"));
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "대표 프로필 업로드 성공!"));;
    }
}
