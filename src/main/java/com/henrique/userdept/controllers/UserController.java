package com.henrique.userdept.controllers;
import com.henrique.userdept.entities.User;
import com.henrique.userdept.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){

        return ResponseEntity.status(HttpStatus.OK).body(userService.listAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        User result = userService.findById(id);

        return result !=null ? ResponseEntity.status(HttpStatus.FOUND).body(result) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user){

        User result = userService.insert(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody User user){

        User data = userService.dataUpdate(user);

        return data != null ? ResponseEntity.status(HttpStatus.OK).body(userService.dataUpdate(data))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Department not found");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        return userService.delete(id) ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.OK).body("User not found");
    }

}
