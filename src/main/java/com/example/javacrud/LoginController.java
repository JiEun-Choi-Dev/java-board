package com.example.javacrud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(
            String userEmail,
            String password,
            HttpSession session
    ){
        if(!"test@email.com".equals(userEmail) || !"1234".equals(password)){
            return "redirect:/login?error";
        }
        session.setAttribute("isLogin",true);
        session.setAttribute("userEmail", userEmail);
        return "redirect:/";
    }
}
