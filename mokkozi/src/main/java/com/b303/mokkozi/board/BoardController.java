package com.b303.mokkozi.board;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.board.request.BoardListGetReq;
import com.b303.mokkozi.board.request.BoardWritePostReq;
import com.b303.mokkozi.board.response.BoardListRes;
import com.b303.mokkozi.board.response.BoardRes;
import com.b303.mokkozi.board.response.BoardWritePostRes;
import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.Board;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.NoSuchElementException;
import java.util.Optional;

@Api(value = "게시판 API", tags = { "Board." })
@RestController
@RequestMapping("/api/meet/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    //게시글 목록 조회
    @GetMapping("")
    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록을 조회한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> getBoardList(
            @RequestBody @ApiParam(value = "게시글 페이지 정보", required = true) BoardListGetReq boardListGetReq
//            ,@ApiIgnore Authentication authentication
    ) {
//        if (authentication == null) {
//            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//        } else {
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//            String userEmail = userDetails.getUserEmail();
//            User user = userService.findByUserEmail(userEmail);
//
//            if (user != null) {
        try{
            Page<BoardDto> boardList = boardService.getBoardList(boardListGetReq);
            return ResponseEntity.ok(BoardListRes.of(200, "게시글 목록 조회 완료.", boardList));
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.ok(BaseResponseBody.of(404, "게시글 결과 없음."));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
//            }

//        }
    }

    //게시글 상세조회
    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시글 상세 조회", notes = "단일 게시글을 조회한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> getBoardDetail(
            @PathVariable("boardId") Long boardId
//            ,@ApiIgnore Authentication authentication
    ) {
//        if (authentication == null) {
//            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//        } else {
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//            String userEmail = userDetails.getUserEmail();
//            User user = userService.findByUserEmail(userEmail);
//
//            if (user != null) {
        try {
            Board board = boardService.getBoardDetail(boardId);
            return ResponseEntity.ok(BoardRes.of(200, "게시글 상세 조회 완료.", board));
        }catch (NoSuchElementException e){
            return ResponseEntity.ok(BaseResponseBody.of(404, "게시글 결과 없음."));
        }catch (Exception e){
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
//            }

//        }
    }


    // 게시글 작성
    @PostMapping("")
    @ApiOperation(value = "게시글 작성", notes = "새로운 게시글 작성한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> writeBoard(
            @RequestBody @ApiParam(value = "게시글 정보", required = true) BoardWritePostReq bwpr
//            ,@ApiIgnore Authentication authentication
    ) {
//        if (authentication == null) {
//            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//        } else {
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//            String userEmail = userDetails.getUserEmail();
//            User user = userService.findByUserEmail(userEmail);
//
//            if (user != null) {

//                 Board board = boardService.createBoard(userId,bwpr);
        try{
            Board board = boardService.createBoard("test@ssafy.com",bwpr);
            return ResponseEntity.status(200).body(BoardWritePostRes.of(200, "게시글 작성 완료", board));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }

//            }

//        }
    }

    // 게시글 수정


    // 게시글 삭제
    @DeleteMapping("")
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> deleteBoard(
            @RequestParam @ApiParam(value = "게시글 ID", required = true) Long boardId
//            ,@ApiIgnore Authentication authentication
    ) {
//        if (authentication == null) {
//            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//        } else {
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//            String userEmail = userDetails.getUserEmail();
//            User user = userService.findByUserEmail(userEmail);
//
//            if (user != null) {

        try {
            boardService.deleteBoard(boardId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "게시글 삭제 완료"));
        }catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "게시글 삭제 실패"));
        }catch (Exception e){
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));

        }

//            }

//        }
    }

    // 게시글 좋아요
    @PostMapping("/like")
    @ApiOperation(value = "게시글 좋아요", notes = "게시글 좋아요 기능을 적용한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> writeBoard(
            @RequestParam @ApiParam(value = "게시글 ID", required = true) Long boardId
//            ,@ApiIgnore Authentication authentication
    ) {
//        if (authentication == null) {
//            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//        } else {
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//            String userEmail = userDetails.getUserEmail();
//            User user = userService.findByUserEmail(userEmail);
//
//            if (user != null) {

        try{
//                 Board board = boardService.createBoard(userId,boardId);

            boardService.createBoardLike(user, boardId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.ok(BaseResponseBody.of(404, "게시글 없음."));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }

//            }

//        }
    }

    // 게시글 검색
    @GetMapping("/search/{type}")
    @ApiOperation(value = "게시글 목록 검색", notes = "게시글 목록을 검색한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> searchBoardList(
            @PathVariable("type") String type, @RequestParam @ApiParam(value = "검색 키워드", required = true) String keyword, @RequestParam @ApiParam(value = "페이지 번호", required = true) int pageIdx
//            ,@ApiIgnore Authentication authentication
    ) {
//        if (authentication == null) {
//            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//        } else {
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//            String userEmail = userDetails.getUserEmail();
//            User user = userService.findByUserEmail(userEmail);
//
//            if (user != null) {
        try{
            Page<BoardDto> boardList = boardService.searchBoardList(type,keyword,pageIdx);
            return ResponseEntity.ok(BoardListRes.of(200, "게시글 검색 완료.", boardList));
        }catch (NullPointerException |NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.ok(BaseResponseBody.of(404, "게시글 검색 결과 없음."));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
//            }

//        }
    }

}
