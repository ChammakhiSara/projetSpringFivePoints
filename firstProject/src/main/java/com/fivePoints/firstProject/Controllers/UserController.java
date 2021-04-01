package com.fivePoints.firstProject.Controllers;

import com.fivePoints.firstProject.Models.User;
import com.fivePoints.firstProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User addUser (@RequestBody  User user){
        return this.userService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id){
        return this.userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser (@PathParam("id") int id){
        return this.userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public User update(@RequestBody User user,@PathParam("id") int id){
       return userService.updateUser(user, id);
    }
}
