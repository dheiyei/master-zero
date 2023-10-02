package com.koke.model.vo;

import com.koke.model.entity.sys.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

/**
 * 用户视图对象类
 * @author koke
 */
@With
@Builder
@Data
@ApiModel(description= "用户视图对象")
public class UserVO implements Serializable {

    private static final long serialVersionUID = 7816183907564882080L;

    @ApiModelProperty(value ="唯一标识",example = "0")
    private Long userId;

    @ApiModelProperty(value ="用户名")
    private String username;

    @ApiModelProperty(value ="昵称")
    private String nickname;

    @ApiModelProperty(value ="是否可用")
    private boolean enabled;

    @ApiModelProperty(value ="是否审批")
    private boolean approved;

    @ApiModelProperty(value ="创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value ="修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value ="角色 id 列表")
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
