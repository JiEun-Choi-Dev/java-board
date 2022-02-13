package com.example.javacrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

//    @PostMapping("/login")
//    public String postLogin(
//            String userEmail,
//            String password,
//            HttpSession session
//    ){
//        UserEntity user = userRepository.findByUserEmail(userEmail)
//                .orElse(null);
//
//        if(user == null){
//            return "redirect:/login?error";
//        }
//
//        if(!user.getPassword().equals(password)){
//            return "redirect:/login?error";
//        }
//
//        session.setAttribute("user", user);
//        return "redirect:/";
//    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");

        return "redirect:/";
    }
}
