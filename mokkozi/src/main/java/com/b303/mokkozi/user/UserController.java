package com.b303.mokkozi.user;

import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.jwt.TokenProvider;
import com.b303.mokkozi.user.dto.TokenDto;
import com.b303.mokkozi.user.dto.UserFollowDto;
import com.b303.mokkozi.user.request.CredentialPostReq;
import com.b303.mokkozi.user.response.UserFollowRes;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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

        // 제일 먼저 사용자 아이디가 유효한지 확인한다.
        Optional<User> user = userService.findByEmail(credentials.getEmail());
        if (user.isPresent()) {
            // 해당 사용자의 패스워드가 일치하면? 토큰 발급해서 return
            if (passwordEncoder.matches(credentials.getPassword(), user.get().getPassword())) {
                // TokenProvider 객체 생성
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.get().getId(), user.get().getPassword());

                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
                return ResponseEntity.ok(TokenDto.of(200, "로그인에 성공하였습니다.", tokenProvider.createToken(authentication)));
            }
            // 비밀번호가 일치하지 않는 경우
            else {
                return ResponseEntity.status(403).body(BaseResponseBody.of(403, "비밀번호가 일치하지 않습니다."));
            }
        }
        // 해당 아이디가 없는 경우..
        else {
            return ResponseEntity.status(406).body(BaseResponseBody.of(406, "회원이 존재하지 않습니다."));
        }
    }

    // 유저 팔로우
    @PostMapping("/follow")
    @ApiOperation(value = "팔로우", notes = "다른 사용자를 팔로우할 수 있다.")
    @ApiResponses({@ApiResponse(code = 200, message = "팔로우 성공"), @ApiResponse(code = 500, message = "팔로우 실패")})
    public ResponseEntity<? extends BaseResponseBody>  follow(@RequestParam @ApiParam(value = "다른 사용자의 이메일", required = true) String toUserEmail
//            ,Authentication
    ) {

        //Jwt를 통해 나의 정보 get

        try{
            User fromUser = new User();
            userService.createFollow(fromUser,toUserEmail);
            return ResponseEntity.ok(BaseResponseBody.of(200, "팔로우 성공"));
        }catch (NullPointerException | NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 사용자입니다."));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
    }

    // 유저 팔로우 취소
    @DeleteMapping("/unfollow")
    @ApiOperation(value = "팔로우 취소", notes = "다른 사용자를 팔로우 취소할 수 있다.")
    @ApiResponses({@ApiResponse(code = 200, message = "언팔로우 성공"), @ApiResponse(code = 500, message = "언팔로우 실패")})
    public ResponseEntity<? extends BaseResponseBody>  deleteFollow(@RequestParam @ApiParam(value = "팔로우 ID", required = true) Long followId
//            ,Authentication
    ) {

        //Jwt를 통해 나의 정보 get

        try{
            userService.deleteFollow(followId);
            return ResponseEntity.ok(BaseResponseBody.of(200, "언팔로우 성공"));
        }catch (NullPointerException | NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 정보입니다."));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
    }

    //나의 팔로워 목록 확인
    @GetMapping("/followers")
    @ApiOperation(value = "팔로워 목록 ", notes = "팔로워 정보를 리스트로 반환")
    @ApiResponses({@ApiResponse(code = 200, message = "팔로워 목록 조회 성공"), @ApiResponse(code = 500, message = "팔로워 목록 조회 실패")})
    public ResponseEntity<? extends BaseResponseBody> getFollowers(
//            Authentication
    ){
        //Jwt를 통해 나의 정보 get
        try{
            User user = new User();
            List<UserFollowDto> followers = userService.getFollowers(user);
            return ResponseEntity.ok(UserFollowRes.of(200, "팔로워 목록 조회 성공",followers));
        }catch (NullPointerException | NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 정보입니다."));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
    }

    //나의 팔로잉 목록 확인
    @GetMapping("/following")
    @ApiOperation(value = "팔로잉 목록 ", notes = "팔로잉 정보를 리스트로 반환")
    @ApiResponses({@ApiResponse(code = 200, message = "팔로워 목록 조회 성공"), @ApiResponse(code = 500, message = "팔로잉 목록 조회 실패")})
    public ResponseEntity<? extends BaseResponseBody> getFollowing(
//            Authentication
    ){
        //Jwt를 통해 나의 정보 get
        try{
            User user = new User();
            List<UserFollowDto> following = userService.getFollowing(user);
            return ResponseEntity.ok(UserFollowRes.of(200, "팔로워 목록 조회 성공",following));
        }catch (NullPointerException | NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 정보입니다."));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
        }
    }

}
