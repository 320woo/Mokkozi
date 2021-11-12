package com.b303.mokkozi.user.dto;

import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRandomDto extends BaseResponseBody {

    private List<User> random;

    public static UserRandomDto of(Integer statusCode, String message, List<User> random) {
        UserRandomDto res = new UserRandomDto();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setRandom(random);
        return res;
    }

}
