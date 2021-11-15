package com.b303.mokkozi.report;

import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.report.dto.ReportUserDto;
import com.b303.mokkozi.report.dto.ReportUserListDto;
import com.b303.mokkozi.report.request.ReportUserPostReq;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/meet/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    // 사용자 신고 등록
    @PostMapping("/user")
    @ApiOperation(value = "사용자 신고", notes = "다른 사용자를 신고할 수 있다.")
    @ApiResponses({@ApiResponse(code = 200, message = "사용자 신고 성공"), @ApiResponse(code = 500, message = "사용자 신고 실패")})
    public ResponseEntity<? extends BaseResponseBody> createUserReport(@RequestBody ReportUserPostReq rupr
            , @ApiIgnore Authentication authentication
    ) {

        try{
            User user = (User) authentication.getDetails();
            reportService.createUserReport(user,rupr);
            return ResponseEntity.ok(BaseResponseBody.of(200, "사용자 신고 성공"));
        } catch (AuthenticationException | NullPointerException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 사용자입니다."));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
    }

    // 관리자용 신고 목록 조회
    @GetMapping("/userList")
    @ApiOperation(value = "사용자 신고 목록 조회", notes = "사용자 신고 목록을 조회할 수 있다.")
    @ApiResponses({@ApiResponse(code = 200, message = "사용자 신고 목록 조회 성공"), @ApiResponse(code = 500, message = "사용자 신고 목록 조회 실패")})
    public ResponseEntity<? extends BaseResponseBody> getUserReportList(@RequestParam @ApiParam(value = "신고 페이지 Index", defaultValue = "0") int page
            , @ApiIgnore Authentication authentication
    ) {

        try{
            User user = (User) authentication.getDetails();
            Page<ReportUserDto> reportList = reportService.getUserReportList(page);
            return ResponseEntity.ok(ReportUserListDto.of(200, "사용자 신고 성공",reportList));
        } catch (AuthenticationException | NullPointerException e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 사용자입니다."));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
    }

    // 관리자용 신고 내용 조회

    // 관리자용 신고 내용 변경


    // 게시판 신고

}
