package com.own.service;

import com.own.additional.RoleChanger;
import com.own.entity.Role;
import com.own.entity.User;
import com.own.exception.UserLoginAlreadyExistsException;
import com.own.exception.UserNotFoundException;
import com.own.repository.RoleRepository;
import com.own.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private PBKDF2Hasher hasher;

    // TODO: uncomment
    @Autowired
    private Pbkdf2PasswordEncoder encoder;

//    @Autowired
//    private PasswordEncoder encoder;

    public User loginUser(User user) throws UserNotFoundException {
        String message = "User with this login does not exists: " + user.getUsername();
        User found = userRepository.findByUsername(user.getUsername()).orElseThrow(() ->new UserNotFoundException(message));
//        if (found == null) {
//            String message = "User with this login does not exists: " + user.getUsername();
//            throw new UserNotFoundException(message);
//        }
//        return hasher.checkPassword(user.getPassword(), login.getPassword());
        if (encoder.matches(user.getPassword(), found.getPassword())) {
            return found;
        } else {
            return null;
        }
    }

    public User signUp(@Valid User user) throws UserLoginAlreadyExistsException {
        System.out.println("SIGNUP" +   user);
//        User existed = userRepository.findByLogin(user.getLogin());
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            String message = "User with this login already exists: " + user.getUsername();
            throw new UserLoginAlreadyExistsException(message);
        }

//        user.setPassword(hasher.hash(user.getPassword()));
//        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();

        user.setRoles(new HashSet<Role>());

        System.out.println("Current password is: " + user.getPassword());
        String s = encoder.encode(user.getPassword());
        System.out.println("Encoded password is: " + s);
        user.setPassword(s);

        assignRole(user, "USER");

        return userRepository.save(user);
    }

    public void changeUser(RoleChanger roleChanger) {
        User copy = userRepository.findByUsername(roleChanger.getUsername()).get();
        assignRole(copy, roleChanger.getRole());
        System.out.println(copy);
        userRepository.save(copy);
    }

    public void assignRole(User user, String role) {

        System.out.println("role " + role);
        System.out.println("repo " + roleRepository.findByName(role).getName());
        Set<Role> set = new HashSet<>();
        set.add(roleRepository.findByName(role));
        user.setRoles(set);
        System.out.println(user);

//        System.out.println(userRepository.findByUsername(user.getUsername()).get());

    }

}
