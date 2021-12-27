package com.own.controller;

import com.own.additional.RoleChanger;
import com.own.entity.User;
import com.own.exception.UserLoginAlreadyExistsException;
import com.own.repository.UserRepository;
import com.own.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addUser(@RequestBody User user) throws UserLoginAlreadyExistsException {
        System.out.println(user);
        userService.signUp(user);
        System.out.println(userRepository.findByUsername(user.getUsername()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void changeUserRole(@RequestBody RoleChanger roleChanger) throws UserLoginAlreadyExistsException {
        System.out.println(roleChanger.toString());
        userService.changeUser(roleChanger);
    }

}
