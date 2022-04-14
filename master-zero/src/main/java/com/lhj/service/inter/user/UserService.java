package com.lhj.service.inter.user;

import com.lhj.model.dto.UpdatePasswordDTO;
import com.lhj.model.dto.UserDTO;
import com.lhj.model.entity.user.User;

import java.util.List;

public interface UserService {

    List<User> selectUsers(User user);

    User selectUserById(Long id);

    void createUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void deleteUsersByIds(List<Long> userIds);

    void updateUserRole(String key);
//    void approveUsers(UserApproveDTO userApproveDTO);
    void updatePassword(UpdatePasswordDTO updatePasswordDTO);
}
