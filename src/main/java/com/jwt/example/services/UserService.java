package com.jwt.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.example.models.User;
import com.jwt.example.repositories.UserRepository;

@Service
public class UserService {
    // private List<User> users = new ArrayList<>();

    // public UserService(){
    //     users.add(new User(UUID.randomUUID().toString(),"Ali", "ali@gmail.com"));
    //     users.add(new User(UUID.randomUUID().toString(),"Adi", "adi@gmail.com"));
    //     users.add(new User(UUID.randomUUID().toString(),"Sandy", "sandy@gmail.com"));

    // }
    // public List<User> getUsers(){
    //     return this.users;
    // }


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User creatUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
