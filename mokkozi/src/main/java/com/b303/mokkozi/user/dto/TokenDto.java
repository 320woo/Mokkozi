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
    private String nickName;
    private String profile;
    private String address;
    private String email;

    public static TokenDto of(Integer statusCode, String message, String token, String nickName, String profile,
    		String address, String email) {
        TokenDto res = new TokenDto();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setToken(token);
        res.setNickName(nickName);
        res.setProfile(profile);
        res.setAddress(address);
        res.setEmail(email);
        return res;
    }
}
