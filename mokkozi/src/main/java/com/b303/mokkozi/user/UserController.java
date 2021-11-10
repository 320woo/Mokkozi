package com.b303.mokkozi.user;

import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.jwt.TokenProvider;
import com.b303.mokkozi.user.dto.TokenDto;
import com.b303.mokkozi.user.request.CredentialPostReq;
import com.b303.mokkozi.user.request.JoinInfoPostReq;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/meet/user")
public class UserController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // Constructor. Bean이 생성될 때 클래스 변수 Initializing
    public UserController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "ID, PW 이용 로그인")
    @ApiResponses({@ApiResponse(code = 200, message = "로그인 성공"), @ApiResponse(code = 500, message = "로그인 실패")})
    public ResponseEntity<? extends BaseResponseBody> login(@RequestBody @ApiParam(value = "회원의 로그인 정보(아이디와 패스워드)", required = true) CredentialPostReq credentials) {

        logger.info("사용자 이메일 정보 {}", credentials.getEmail());
        logger.info("사용자 비밀번호 정보 {}", credentials.getPassword());

        // 1. 제일 먼저 계정이 존재하는지 확인한다.
        Optional<User> user = userService.findByEmail(credentials.getEmail());
        logger.info("불러온 사용자 객체는.. {}", user);

        // 2. 해당 계정이 존재하면
        if (user.isPresent()) {
            // 3. 비밀번호를 비교한다.
            logger.info("비밀번호를 비교합니다.");
            if (passwordEncoder.matches(credentials.getPassword(), user.get().getPassword())) {
                logger.info("비밀번호 일치합니다.");
                // TokenProvider 객체 생성
                return ResponseEntity.ok(TokenDto.of(200, "로그인에 성공하였습니다. 토큰 발급 완료", tokenProvider.createToken(user.get().getEmail())));
            }
            // 비밀번호가 일치하지 않는 경우
            else {
                return ResponseEntity.status(403).body(BaseResponseBody.of(403, "비밀번호가 일치하지 않습니다."));
            }
        }
        // 해당 아이디가 없는 경우
        else {
            return ResponseEntity.status(406).body(BaseResponseBody.of(406, "회원이 존재하지 않습니다."));
        }
    }

    @PostMapping("/join")
    @ApiOperation(value = "회원가입", notes = "회원 가입에 필요한 정보를 입력하고 회원가입한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "회원가입 성공"), @ApiResponse(code = 500, message = "회원가입 실패")})
    public ResponseEntity<? extends BaseResponseBody> join(@RequestBody @ApiParam(value="회원가입 시 필요한 정보") JoinInfoPostReq joinInfo) {
        logger.info("회원가입 시 받아온 정보는 {}", joinInfo.toString());

        // 사용자가 입력한 암호를 한번 인코딩해야 한다.
        joinInfo.setPassword(passwordEncoder.encode(joinInfo.getPassword()));

        try {
            User result = userService.join(joinInfo);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원가입 성공."));
        }
        catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원가입 실패."));
        }
    }
}
