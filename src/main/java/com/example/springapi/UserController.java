package com.example.springapi;


import com.example.springapi.api.model.User;
import com.example.springapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/get")
    public ResponseEntity<User> getUser(@RequestParam int id) {
        User res = userService.getUser(id);
        if (res == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @GetMapping("/users/valid/username")
    public ResponseEntity<Boolean> getIsValidUsername(@RequestParam String username) {
        return new ResponseEntity<>(UserService.isValidUsername(username), HttpStatus.OK);
    }

    @GetMapping("/users/valid/password")
    public ResponseEntity<Boolean> getIsValidPassword(@RequestParam String password) {
        return new ResponseEntity<>(UserService.isValidPassword(password), HttpStatus.OK);
    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/add")
    public ResponseEntity<User> addUser(@RequestParam String name, String email, String username, String password) {
        if (UserService.isValidUsername(username) && UserService.isValidPassword(password)) {
            return new ResponseEntity<>(userService.addUser(name, email, username, password), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
