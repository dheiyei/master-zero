package com.koke.model.vo;

import com.koke.model.entity.sys.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@With
@Builder
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 7816183907564882080L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 是否可用
     */
    private boolean enabled;

    /**
     * 是否审批
     */
    private boolean approved;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 角色 id 列表
     */
    private List<Long> roleIds;

    /**
     * 将 {@link User} 转化为 {@link UserVO}
     */
    public static Function<User, UserVO> fromUser = (user) -> UserVO.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .nickname(user.getNickname())
            .enabled(user.isEnabled())
            .approved(user.isApproved())
            .createTime(user.getCreateTime())
            .updateTime(user.getUpdateTime())
            .build();

}
