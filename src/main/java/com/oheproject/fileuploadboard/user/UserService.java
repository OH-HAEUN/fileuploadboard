package com.oheproject.fileuploadboard.user;

public interface UserService {
    public int checkUsername(UserDTO dto);
    // 회원가입
    public void insertUser(UserDTO user, String password);

    public UserDTO currentName(String username);

    public UserDTO userInfo(String username);
}
