package com.jwt.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.example.models.User;
import com.jwt.example.services.UserService;


@RestController
public class HomeController {
    
    @Autowired
    private UserService userService;
    @GetMapping("/home")
    @ResponseBody
    public String home(){

        return "Home";
    }

    @GetMapping("/getusers")
    public List<User> getUsers(){
        System.out.println("Getting users");
        return this.userService.getUsers();
    }


}
