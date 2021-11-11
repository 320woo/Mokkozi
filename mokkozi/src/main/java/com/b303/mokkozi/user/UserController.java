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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        // ID, PW가 담긴 토큰 발급
        logger.info("UserController.login 65 : 이메일: {}, 비밀번호: {}", credentials.getEmail(), credentials.getPassword());
        logger.info("UserController.login 66 : Authenticaion 토큰 생성. 이 과정에서 해당 사용자가 존재하는지, 비밀번호는 맞는지를 확인합니다.");
        // 아이디 또는 비밀번호가 맞지 않는 경우 에러 메시지를 출력한다. (Logger 형태)
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());

        // 이 토큰을 이용해서 Authenticaion 객체 생성
        logger.info("UserController.login 69 : Authenticaion 객체 생성");
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // Spring Security에 해당 authenticaion 객체를 저장한다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 생성한 authenticaion 객체를 이용하여 JWT 토큰을 발급받는다.
        logger.info("UserController.login 76 : 토큰 발급 완료! : {}", authentication);
        return ResponseEntity.ok(TokenDto.of(200, "로그인에 성공하였습니다. 토큰 발급 완료", tokenProvider.createToken(authentication, "user")));
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

    @PostMapping("/test")
    public ResponseEntity<? extends BaseResponseBody> test(Authentication authentication) {
        logger.info("테스트합니다.");
        logger.info("Authentication.getName() : {}", authentication.getName());
        return null;
    }

}
