package com.b303.mokkozi.board;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.board.request.BoardModifyPatchReq;
import com.b303.mokkozi.board.request.BoardWritePostReq;
import com.b303.mokkozi.board.dto.BoardListDto;
import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.User;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.NoSuchElementException;

@Api(value = "게시판 API", tags = {"Board."})
@RestController
@RequestMapping("/api/meet/board")
public class BoardController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BoardService boardService;

    //게시글 목록 조회
    @GetMapping("")
    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"), @ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> getBoardList(

            @RequestParam @ApiParam(value = "게시글 페이지 Index", defaultValue = "0") int page
            , @ApiIgnore Authentication authentication
    ) {
        logger.info("BoardController.getBoardList 46 : 함수 시작.");

        try {
            User user = (User) authentication.getDetails();
            logger.info("BoardController.getBoardList 50 : User : {}", user.getEmail());
//            if(user!=null){}
            Page<BoardDto> boardList = boardService.getBoardList(user,page);
            logger.info("BoardController.getBoardList 53 : boardList : {}", boardList);
            return ResponseEntity.ok(BoardListDto.of(200, "게시글 목록 조회 완료.", boardList));
        } catch (AuthenticationException | NullPointerException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "게시글 결과 없음."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }

    }

    //게시글 상세조회
    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시글 상세 조회", notes = "단일 게시글을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"), @ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> getBoardDetail(
            @PathVariable("boardId") Long boardId
            , @ApiIgnore Authentication authentication
    ) {

        try {
            User user = (User) authentication.getDetails();
            BoardDto board = boardService.getBoardDetail(user,boardId);
            return ResponseEntity.ok(BoardDto.of(200, "게시글 상세 조회 완료.", board));
        } catch (AuthenticationException | NullPointerException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "게시글 결과 없음."));
        } catch (Exception e) {
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
    }


    // 게시글 작성
    @PostMapping("")
    @ApiOperation(value = "게시글 작성", notes = "새로운 게시글 작성한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"), @ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> writeBoard(
            @RequestBody @ApiParam(value = "게시글 정보", required = true) BoardWritePostReq bwpr
            , @ApiIgnore Authentication authentication
    ) {
        try {
            User user = (User) authentication.getDetails();
            BoardDto board = boardService.createBoard(user, bwpr);
            return ResponseEntity.ok(BoardDto.of(200, "게시글 작성 완료", board));
        } catch (AuthenticationException | NullPointerException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
    }

    // 게시글 수정
    @PatchMapping("")
    @ApiOperation(value = "게시글 수정", notes = "게시글 정보를 수정한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"), @ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> modifyBoard(
            @RequestBody @ApiParam(value = "게시글 수정 정보", required = true) BoardModifyPatchReq bmpr
            , @ApiIgnore Authentication authentication
    ) {

        try {
            User user = (User) authentication.getDetails();
            BoardDto board = boardService.modifyBoard(user, bmpr);
            return ResponseEntity.ok(BoardDto.of(200, "게시글 수정 완료.", board));
        } catch (AuthenticationException | NullPointerException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "게시글 결과 없음."));
        } catch (Exception e) {
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }

    }


    // 게시글 삭제
    @DeleteMapping("")
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"), @ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> deleteBoard(
            @RequestParam @ApiParam(value = "게시글 ID", required = true) Long boardId
            ,@ApiIgnore Authentication authentication
    ) {
        try {
            User user = (User) authentication.getDetails();
            logger.info("BoardController.boardId 146 : boardId : {}", boardId);
            boardService.deleteBoard(boardId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "게시글 삭제 완료"));
        } catch (AuthenticationException | NullPointerException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "게시글 삭제 실패"));
        } catch (Exception e) {
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));

        }

    }

    // 게시글 좋아요
    @PostMapping("/like")
    @ApiOperation(value = "게시글 좋아요", notes = "게시글 좋아요 기능을 적용한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"), @ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> boardLike(
            @RequestParam @ApiParam(value = "게시글 ID", required = true) Long boardId
            ,@ApiIgnore Authentication authentication
    ) {

        try {
            User user = (User) authentication.getDetails();

            boardService.createBoardLike(user, boardId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
        } catch (AuthenticationException | NullPointerException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "게시글 없음."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }

    }

    // 게시글 좋아요 취소
    @DeleteMapping("/unlike")
    @ApiOperation(value = "게시글 좋아요 취소", notes = "게시글 좋아요 기능을 취소한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"), @ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> boardUnlike(
            @RequestParam @ApiParam(value = "게시글 ID", required = true) Long boardId
            ,@ApiIgnore Authentication authentication
    ) {
        try {
            User user = (User) authentication.getDetails();

            boardService.deleteBoardLike(user, boardId);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "success"));
        } catch (AuthenticationException | NullPointerException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "게시글 없음."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
    }

    /* 게시글 좋아요 확인?
    -> 이걸 GetMapping으로 따로 뺄지
        아니면 게시글 상세조회에서 정보를 포함하여 함께 리턴할지
    * */


    // 게시글 검색
    @GetMapping("/search/{type}")
    @ApiOperation(value = "게시글 목록 검색", notes = "게시글 목록을 검색한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 401, message = "로그인 인증 실패"), @ApiResponse(code = 403, message = "잘못된 요청")})
    public ResponseEntity<? extends BaseResponseBody> searchBoardList(
            @PathVariable("type") String type, @RequestParam @ApiParam(value = "검색 키워드", required = true) String
            keyword, @RequestParam @ApiParam(value = "페이지 번호", required = true) int pageIdx
            ,@ApiIgnore Authentication authentication
    ) {
        try {
            User user = (User) authentication.getDetails();
            Page<BoardDto> boardList = boardService.searchBoardList(user,type, keyword, pageIdx);
            return ResponseEntity.ok(BoardListDto.of(200, "게시글 검색 완료.", boardList));
        } catch (AuthenticationException | NullPointerException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "게시글 검색 결과 없음."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
    }

}
