package com.roleAuth.roleAuthorisation.Controller;

import com.roleAuth.roleAuthorisation.Model.User;
import com.roleAuth.roleAuthorisation.Service.UserService;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c1")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/us1")
    public ResponseEntity registerUser(@RequestBody User user){
        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getUser(@PathVariable long id){
        userService.getUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
        userService.getAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity deleteUser(@PathVariable String userName){
        userService.deleteUser(userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
