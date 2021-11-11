package com.b303.mokkozi.user.response;

import com.b303.mokkozi.common.response.BaseResponseBody;
import com.b303.mokkozi.user.dto.UserFollowDto;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("UserFollowersResponse")
public class UserFollowRes extends BaseResponseBody {

    private List<UserFollowDto> followers;

    public static UserFollowRes of(Integer statusCode, String message, List<UserFollowDto> followers) {
        UserFollowRes res = new UserFollowRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setFollowers(followers);
        return res;
    }
}
