package com.b303.mokkozi.user.dto;


import com.b303.mokkozi.common.response.BaseResponseBody;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto extends BaseResponseBody {

    private String token;

    public static TokenDto of(Integer statusCode, String message, String token) {
        TokenDto res = new TokenDto();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setToken(token);
        return res;
    }
}
