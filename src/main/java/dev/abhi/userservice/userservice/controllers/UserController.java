package dev.abhi.userservice.userservice.controllers;

import dev.abhi.userservice.userservice.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService ;

    public UserController(UserService userService){
        this.userService = userService ;
    }



}
