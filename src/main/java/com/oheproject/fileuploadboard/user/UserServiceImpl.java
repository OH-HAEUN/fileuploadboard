package com.oheproject.fileuploadboard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원 가입
    @Override
    public void insertUser(UserDTO user, String password) {
        user.setPassword( passwordEncoder.encode( password ) );
        userDAO.insertUser( user );
    }

    @Override
    public UserDTO currentName(String username) {
        return userDAO.currentName(username);
    }

    // 아이디 중복 체크
    @Override
    public int checkUsername(UserDTO user) {
        int result = userDAO.checkUsername( user );

        return result;
    }

    @Override
    public UserDTO userInfo(String username) {
        return userDAO.userInfo( username );
    }




}
