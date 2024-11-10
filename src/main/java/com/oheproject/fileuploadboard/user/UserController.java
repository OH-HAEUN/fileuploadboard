package com.oheproject.fileuploadboard.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    // 회원가입 폼
    @GetMapping("/join")
    public String userJoin() {
        return "UserJoin";
    }

    // 회원가입 프로세스
    @PostMapping("/join") 
    public String insertUser(UserDTO dto, Model model, HttpServletRequest request) {
        String password = request.getParameter("password");

        System.out.println(password);

        model.addAttribute("message", "회원가입 되었습니다.");
        model.addAttribute("url", "/");

        userService.insertUser(dto, password);

        return "message";
    }

    // 회원가입 아이디 중복체크
    @ResponseBody
    @PostMapping("/checkUsername")
    public int checkUsername(UserDTO dto) {
        int result = userService.checkUsername(dto);
        return result;
    }

    // 로그인 폼
    @RequestMapping("/login")
    public String userLogin() {
        return "Login";
    }

    // 로그인 실패
    @PostMapping("/loginfail")
    public String loginProcess(Model model) {
        model.addAttribute("message", "로그인 실패");
        model.addAttribute("url", "/login");
        return "message";
    }
}
