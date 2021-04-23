package com.fivePoints.firstProject.Controllers;

import com.fivePoints.firstProject.Models.User;
import com.fivePoints.firstProject.Payload.Responses.ResponseMessage;
import com.fivePoints.firstProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //methode POST with URL to add new user
    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User addUser = this.userService.addNewUser(user);
        return new ResponseEntity<>(addUser, HttpStatus.CREATED);
    }

    //methode GET with URL to find one user
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id){
        User user = this.userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //methode GET with URL to get all the users from dataBase
    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){
        List<User> listUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    //methode DELETE with URL to delete one user
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<ResponseMessage> deleteUser (@PathVariable("id") int id){
        String message = this.userService.deleteUserById(id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

    //methode PUT with URL to update existing user
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("id") int id){
        User updatedUser = this.userService.updateUserById(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //affect role to user
    @PutMapping("/affect_role/{user_id}/{role_id}")
    public ResponseEntity<ResponseMessage> affectUserToRole(int user_id, int role_id) {
        String message = this.userService.affectUserToRole(user_id, role_id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

    //methode GET to find user by first name and email version 1
    @RequestMapping(method = RequestMethod.GET, value = "/V1/{email}/{firstName}")
    public User getUserByEmailAndFirstName(@PathVariable("email") String email, @PathVariable("firstName") String firstName){
        return this.userService.getUserByEmailAndFirstName(email, firstName);
    }

    //methode GET to find user by first name and email version 2
    @RequestMapping(method = RequestMethod.GET, value = "/V2/{email}/{firstName}")
    public User getUserByEmailAndFtName(@PathVariable("email") String email, @PathVariable("firstName") String firstName){
        return this.userService.getUserByEmailAndFtName(email, firstName);
    }
}


