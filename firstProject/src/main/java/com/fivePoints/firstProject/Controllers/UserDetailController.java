package com.fivePoints.firstProject.Controllers;

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
public class UserDetailController {

    @Autowired
    UserDetailService userDetailService;

    @PostMapping("/")
    public ResponseEntity<UserDetail> addUserDetail (@RequestBody UserDetail userDetail){
        UserDetail addUserDetail = this.userDetailService.saveUserDetails(userDetail);
        return new ResponseEntity<>(addUserDetail, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id){
        UserDetail userDetail = this.userDetailService.getUserDetailsById(id);
        return new ResponseEntity<>(userDetail, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDetail>> getUserDetails(){
        List<UserDetail> listUserDetails = this.userDetailService.getUsersDetails();
        return new ResponseEntity<>(listUserDetails, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<ResponseMessage> deleteUserDetail (@PathVariable("id") int id){
        String message = this.userDetailService.deleteUserDetails(id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<UserDetail> updateUserDetail(@RequestBody UserDetail userDetail,@PathVariable("id") int id){
        UserDetail updatedUserDetail = this.userDetailService.updateUserDetails(userDetail, id);
        return new ResponseEntity<>(updatedUserDetail, HttpStatus.OK);
    }
}
