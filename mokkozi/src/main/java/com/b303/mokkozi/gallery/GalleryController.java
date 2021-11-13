package com.b303.mokkozi.gallery;

import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.config.S3Uploader;
import com.b303.mokkozi.entity.Gallery;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.gallery.request.FormWrapper;
import com.b303.mokkozi.gallery.request.GalleryVO;
import com.b303.mokkozi.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/meet/gallery")
public class GalleryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private S3Uploader s3Uploader;

    @Autowired
    private UserService userService;

    @Autowired
    GalleryService galleryService;

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
            @RequestBody(required = true) MultipartFile file,
            @RequestBody String sort,
            @RequestBody String id) {

        String file_path = "";
        try {
            // 1. S3에 먼저 업로드한다.
            file_path = s3Uploader.upload(file, file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "파일 업로드 실패하였습니다.."));
        }
        // 2. S3에 업로드한 URL을 file_path로 하여 DB에 저장한다.
        if (!file_path.equals("")) {
            GalleryVO galleryVo = new GalleryVO();
            galleryVo.setFilePath(file_path);
            galleryVo.setSort(sort);
            galleryVo.setTitle(file.getOriginalFilename());

            Gallery result = galleryService.galleryCreate(galleryVo, id);
            logger.info("GalleryController.upload 65 : 파일 저장 결과 : {}", result);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "파일 S3 업로드 및 DB 저장 완료")); 
        }
        // 이 경우는 file_path가 ""인 경우로, 정상적으로 S3에 업로드 되지 않았다는 뜻이다.
        return ResponseEntity.status(500).body(BaseResponseBody.of(500, "저장된 파일의 S3 URL이 Null입니다."));
    }


    /**
     * 사용자의 대표 프로필을 S3에 업로드하고, DB에 저장한다.
     * @Params file, userEmail
     * @Return Response Message
     */
    @PostMapping("/myProfile")
    @Transactional
    @ApiOperation(value = "사용자 대표 프로필 업로드", notes = "회원가입시 첨부한 대표 프로필을 S3에 올리고, User 객체의 profile 을 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "대표 프로필 업로드 성공"),
            @ApiResponse(code = 500, message = "대표 프로필 업로드 실패"),
            @ApiResponse(code = 401, message = "권한 인증 실패")})
    public ResponseEntity<? extends BaseResponseBody> uploadMyProfile(
            @ModelAttribute FormWrapper model) {

        logger.info("GalleryController.uploadMyProfile 87 : 파일 용량 정보 : {}", model.getFile().getSize());
        logger.info("GalleryController.uploadMyProfile 88 : 파일 컨텐츠 타입 : {}", model.getFile().getContentType());
        logger.info("GalleryController.uploadMyProfile 89 : 파일 원래 이름 : {}", model.getFile().getOriginalFilename());
        logger.info("GalleryController.uploadMyProfile 91 : getName : {}", model.getFile().getName());
        logger.info("GalleryController.uploadMyProfile 92 : 회원가입한 사용자 이메일 정보 : {}", model.getEmail());

        String file_path = "";
        // 1. S3에 업로드하기
        try {
            logger.info("GalleryController.uploadMyProfile 100 : S3에 파일 업로드합니다.");
            // 2번째 인자는 폴더이름이다.
            file_path = s3Uploader.upload(model.getFile(), "static");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "대표 프로필 업로드 실패"));
        }

        if (!file_path.equals("")) {
            // User 객체의 profile 컬럼 속성을 업데이트한다.
            logger.info("GalleryController.uploadMyProfile 109 : User profile 속성 수정합니다.");
            User user = userService.findByEmail(model.getEmail()).get();
            user.setProfile(file_path);
            userService.userUpdate(user);
        }
        else {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "대표 프로필 업로드 실패. file_path Null"));
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "대표 프로필 업로드 성공!"));
    }
}



