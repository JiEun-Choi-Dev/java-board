package com.example.javacrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(
            String userEmail,
            String password
    ){
        UserEntity user = new UserEntity();
        user.setUserEmail(userEmail);
        user.setPassword(password);

        userRepository.save(user);

        return "redirect:/";
    }

}
