package com.may54ther.user.model.service;


import com.may54ther.user.model.dto.UserDTO;
import com.may54ther.user.model.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

import static com.may54ther.common.Template.*;

public class UserService {

    private UserMapper userMapper;

    public UserDTO login(UserDTO loginUser) {
        SqlSession sqlSession = getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);

        UserDTO user = userMapper.selectByIdAndPassword(loginUser);
        sqlSession.close();

        return user;
    }

    public boolean registerUser(UserDTO newUser) {
        SqlSession sqlSession = getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);

        if (userMapper.existsById(newUser.getId()) > 0) {
            System.err.println("이미 존재하는 아이디 입니다.");
            return false;
        }

        int result = userMapper.insertUser(newUser);
        if (result > 0) {
            commit(sqlSession);
        } else {
            rollback(sqlSession);
        }

        return result > 0;
    }

    public boolean modifyUser(UserDTO modifyUser) {
        SqlSession sqlSession = getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);

        int result = userMapper.updateUser(modifyUser);
        if (result > 0) {
            commit(sqlSession);
        } else {
            rollback(sqlSession);
        }

        return result > 0;
    }

    public boolean deleteUser(int userCode) {
        SqlSession sqlSession = getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);

        int result = userMapper.deleteUser(userCode);
        if (result > 0) {
            commit(sqlSession);
        } else {
            rollback(sqlSession);
        }

        return result > 0;
    }
}
