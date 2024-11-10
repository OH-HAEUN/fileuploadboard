package com.oheproject.fileuploadboard.user;

public interface UserMapper {
    UserDTO selectUser(String user, String password);
    int insertUser(UserDTO dto);
}
