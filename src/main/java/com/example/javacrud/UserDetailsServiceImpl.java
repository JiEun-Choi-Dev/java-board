package com.example.javacrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "is not found."));
        return User.builder()
                .username(user.getUserEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
