package com.b303.mokkozi.user;

import com.b303.mokkozi.common.response.BaseResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/meet/user")
public class UserController {
    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "ID, PW 이용 로그인")
    @ApiResponses({@ApiResponse(code = 200, message = "로그인 성공"), @ApiResponse(code = 500, message = "로그인 실패")})
    public ResponseEntity<? extends BaseResponseBody> login() {
        // 로그인 로직 작성
        // 로그인에 성공하는 경우 JWT를 반환한다.
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
    }
}
