package com.fivePoints.firstProject.Services;

import com.fivePoints.firstProject.Exceptions.ResourceNotFoundException;
import com.fivePoints.firstProject.Models.Post;
import com.fivePoints.firstProject.Models.User;
import com.fivePoints.firstProject.Models.UserDetail;
import com.fivePoints.firstProject.Repositries.UserDetailsRepository;
import com.fivePoints.firstProject.Repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserRepository userRepository;

    //add a new details of user to the dataBase
    public  String addNewUserDetail(UserDetail userDetail , int user_id) throws ResourceNotFoundException {
        Optional<User> userdata = this.userRepository.findById(user_id);
        if(userdata.isPresent()){
            User user = userdata.orElseThrow(()-> new ResourceNotFoundException("user not found"));
            userDetail.setUser(user);

            this.userDetailsRepository.save(userDetail);
            return "user detail ajouté avec succés";
        }
        else {
            throw new  ResourceNotFoundException("user not found");
        }
    }

    //get all the list of details added to the dataBase
    public List<UserDetail> getUsersDetails() {

        return userDetailsRepository.findAll();
    }

    //delete one user detail by his id
    public String deleteUserDetails(int id) {
        Optional<UserDetail> userDetailsData = this.userDetailsRepository.findById(id);
        if (userDetailsData.isPresent()){
            this.userDetailsRepository.deleteById(id);
            return "deleted successfully";
        }else {
            throw new ResourceNotFoundException("user detail not found");
        }
    }

    //get or find one user detail by his id
    public UserDetail getUserDetailsById(int id) {
        Optional<UserDetail> userDetailsData = this.userDetailsRepository.findById(id);
        return userDetailsData.orElseThrow(() -> new ResourceNotFoundException("user details not found"));
    }

    //update the userDetail by his id and the object userDetail wanted to update
    public UserDetail updateUserDetails(UserDetail userDetail, int id) {
        Optional<UserDetail> userDetailsData = this.userDetailsRepository.findById(id);
        if (userDetailsData.isPresent()) {
            UserDetail currentUserDetails = userDetailsData.orElse(null);
            currentUserDetails.setAge(userDetail.getAge());
            currentUserDetails.setDateNaissance(userDetail.getDateNaissance());
            currentUserDetails.setGithubLink(userDetail.getGithubLink());
            currentUserDetails.setLinkedinLink(userDetail.getLinkedinLink());

            UserDetail updatedUserDetails = this.userDetailsRepository.save(currentUserDetails);
            return updatedUserDetails;
        } else {
            throw new ResourceNotFoundException("user details not found");
        }
    }
}
