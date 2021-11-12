package com.b303.mokkozi.user.dto;

import com.b303.mokkozi.common.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class UserFollowDto {

    private Long id;

    private Long userId; // 사용자 ID

    private String nickname; // 사용자 닉네임

    private String profileUrl; // 프로필 url

    public UserFollowDto(Long id, Long userId, String nickname, String profileUrl){
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }

    @Getter
    @Setter
    @ApiModel("UserFollowersResponse")
    public static class UserFollowListDto extends BaseResponseBody {

        private List<UserFollowDto> followers;

        public static UserFollowListDto of(Integer statusCode, String message, List<UserFollowDto> followers) {
            UserFollowListDto res = new UserFollowListDto();
            res.setStatusCode(statusCode);
            res.setMessage(message);
            res.setFollowers(followers);
            return res;
        }
    }
}
