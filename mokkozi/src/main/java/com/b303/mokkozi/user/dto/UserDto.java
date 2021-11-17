package com.b303.mokkozi.user.dto;

import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseResponseBody {
    // 사용자 PK, 이메일, 닉네임, 유형, 프로필, 상태(active) 정도만 반환하자.
    private User user;
    // 토큰도 함께 보내야 한다.
    private String token;

    public static UserDto of(Integer statusCode, String message, User user, String token) {
        UserDto res = new UserDto();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUser(user);
        res.setToken(token);

        return res;
    }

    public static UserDto of(Integer statusCode, String message, User user) {
        UserDto res = new UserDto();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUser(user);

        return res;
    }
}
