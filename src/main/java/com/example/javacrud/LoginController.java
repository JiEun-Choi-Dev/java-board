package com.example.javacrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "pages/login";
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
    public String logout(HttpServletRequest request, HttpServletResponse response){
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
//        session.removeAttribute("user");
        return "redirect:/";
    }
}
