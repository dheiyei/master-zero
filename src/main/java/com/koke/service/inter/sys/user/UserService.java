package com.koke.service.inter.sys.user;

import com.koke.model.dto.UpdatePasswordDTO;
import com.koke.model.dto.UserDTO;
import com.koke.model.entity.sys.user.User;

import java.util.List;

public interface UserService {

    List<User> selectUsers(User user);

    User selectUserById(Long id);

    void createUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void deleteUsersByIds(List<Long> userIds);

    void updateUserRole(String key);

    void updatePassword(UpdatePasswordDTO updatePasswordDTO);
}
