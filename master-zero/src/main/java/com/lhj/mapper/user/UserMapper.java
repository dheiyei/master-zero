package com.lhj.mapper.user;

import com.lhj.model.dto.UserDTO;
import com.lhj.model.entity.user.User;

import java.util.List;

public interface UserMapper {

    List<User> selectUsers(User user);

    User selectUserById(Long userId);

    User selectByUsername(String username);

    void createUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void deleteUsersByIds(List<Long> userIds);

    void updatePassword(Long userId, String newPassword);
}
