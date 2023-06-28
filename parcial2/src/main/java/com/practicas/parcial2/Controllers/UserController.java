package com.practicas.parcial2.Controllers;

import com.practicas.parcial2.Models.User;
import com.practicas.parcial2.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User userSaved = (userService.saveUser(user));
        if(Objects.isNull(userSaved)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or email already exists");
        }else
            return ResponseEntity.ok(userSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
}
