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

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public String deleteUser (@PathVariable("id") int id){
        return this.userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public User update(@RequestBody User user,@PathVariable("id") int id){
       return userService.updateUser(user, id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/V1/{email}/{firstName}")
    public User getUserByEmailAndFirstName(@PathVariable("email") String email, @PathVariable("firstName") String firstName){
        return this.userService.getUserByEmailAndFirstName(email, firstName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/V2/{email}/{firstName}")
    public User getUserByEmailAndFtName(@PathVariable("email") String email, @PathVariable("firstName") String firstName){
        return this.userService.getUserByEmailAndFtName(email, firstName);
    }
}


