package com.oheproject.fileuploadboard.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private SqlSession sqlSession;

    // 회원가입
    public void insertUser(UserDTO user){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.insertUser(user);
    }

    // 아이디 중복체크
    public int checkUsername(UserDTO user) {
        int result = sqlSession.selectOne("com.oheproject.fileuploadboard.user.UserMapper.checkUsername", user);
        return result;
    }

    // 로그인
    public CustomUserDetails selectUser(String username) {
        return sqlSession.selectOne("com.oheproject.fileuploadboard.user.UserMapper.selectUser",username);
    }

    // 글 작성자 및 로그인 회원정보
    public UserDTO userInfo(String username) {
        return sqlSession.selectOne("com.oheproject.fileuploadboard.user.UserMapper.userInfo",username);
    }

    public UserDTO currentName(String username) {
        return sqlSession.selectOne("com.oheproject.fileuploadboard.user.UserMapper.currentName",username);
    }

    //    public UserDTO selectUser(String username) {
//        System.out.println("userDAO");
//        System.out.println(username);
//
//        UserDTO userDTO = sqlSession.selectOne("com.oheproject.fileuploadboard.user.UserMapper.selectUser",username);
//
//        System.out.println(userDTO.getUsername());
//        System.out.println(userDTO.getPassword());
//
//        return userDTO;
//    }
}
