package com.fivePoints.firstProject.Controllers;

import com.fivePoints.firstProject.Models.Role;
import com.fivePoints.firstProject.Models.User;
import com.fivePoints.firstProject.Payload.Responses.ResponseMessage;
import com.fivePoints.firstProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> addUser (@RequestBody User user){
        User addUser = this.userService.saveUser(user);
        return new ResponseEntity<>(addUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id){
        User user = this.userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        List<User> listUsers = this.userService.getUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<ResponseMessage> deleteUser (@PathVariable("id") int id){
        String message = this.userService.deleteUser(id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("id") int id){
        User updatedUser = this.userService.updateUser(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/V1/{email}/{firstName}")
    public User getUserByEmailAndFirstName(@PathVariable("email") String email, @PathVariable("firstName") String firstName){
        return this.userService.getUserByEmailAndFirstName(email, firstName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/V2/{email}/{firstName}")
    public User getUserByEmailAndFtName(@PathVariable("email") String email, @PathVariable("firstName") String firstName){
        return this.userService.getUserByEmailAndFtName(email, firstName);
    }
}


