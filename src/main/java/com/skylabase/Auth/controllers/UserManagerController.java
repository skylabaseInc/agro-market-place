package com.skylabase.Auth.controllers;

import com.skylabase.Auth.services.AuthenticationService;
import com.skylabase.model.User;
import com.skylabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class UserManagerController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<String> login(@RequestParam(value = "email") String email,
                                        @RequestParam(value = "id") String id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (user.getEmail().equals(email)) {
            String returnValue = authService.createAccessToken(user);
            return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public ResponseEntity<Void> logout(@RequestParam(value = "token") String token) {
        try {
            authService.removeAccessToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
