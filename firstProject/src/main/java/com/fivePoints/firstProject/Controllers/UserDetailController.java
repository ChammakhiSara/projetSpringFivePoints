package com.fivePoints.firstProject.Controllers;

import com.fivePoints.firstProject.Models.Post;
import com.fivePoints.firstProject.Models.User;
import com.fivePoints.firstProject.Models.UserDetail;
import com.fivePoints.firstProject.Payload.Responses.ResponseMessage;
import com.fivePoints.firstProject.Services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userDetails")
@CrossOrigin(origins = "*")
public class UserDetailController {

    @Autowired
    UserDetailService userDetailService;

    //methode POST with URL to add new userDetail
    @PostMapping("/{user_Id}")
    public ResponseEntity<ResponseMessage> saveUserDetail(@RequestBody UserDetail userDetail, @PathVariable(value = "user_Id")int user_Id)
    {
        String message =  this.userDetailService.addNewUserDetail(userDetail, user_Id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.CREATED);
    }

    //methode GET with URL to find one userDetail
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id){
        UserDetail userDetail = this.userDetailService.getUserDetailsById(id);
        return new ResponseEntity<>(userDetail, HttpStatus.OK);
    }

    //methode GET with URL to get all the userDetails from dataBase
    @GetMapping("/")
    public ResponseEntity<List<UserDetail>> getUserDetails(){
        List<UserDetail> listUserDetails = this.userDetailService.getUsersDetails();
        return new ResponseEntity<>(listUserDetails, HttpStatus.OK);
    }

    //methode DELETE with URL to delete one userDetail
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<ResponseMessage> deleteUserDetail (@PathVariable("id") int id){
        String message = this.userDetailService.deleteUserDetails(id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

    //methode PUT with URL to update existing userDetail
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<UserDetail> updateUserDetail(@RequestBody UserDetail userDetail,@PathVariable("id") int id){
        UserDetail updatedUserDetail = this.userDetailService.updateUserDetails(userDetail, id);
        return new ResponseEntity<>(updatedUserDetail, HttpStatus.OK);
    }
}
