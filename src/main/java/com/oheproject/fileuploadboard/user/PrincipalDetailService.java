package com.oheproject.fileuploadboard.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUserDetails customUserDetails = userDAO.selectUser(username);

        if(customUserDetails == null){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }else {
            return customUserDetails;
        }
    }
}
