package com.may54ther.user.model.mapper;

import com.may54ther.user.model.dto.UserDTO;

public interface UserMapper {

    UserDTO selectByIdAndPassword(UserDTO loginUser);

    int existsById(int code);

    int existsById(String id);

    int insertUser(UserDTO newUser);

    int updateUser(UserDTO modifyUser);

    int deleteUser(int userCode);

}
