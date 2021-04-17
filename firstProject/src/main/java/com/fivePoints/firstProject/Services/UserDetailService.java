package com.fivePoints.firstProject.Services;

import com.fivePoints.firstProject.Exceptions.ResourceNotFoundException;
import com.fivePoints.firstProject.Models.UserDetail;
import com.fivePoints.firstProject.Repositries.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public UserDetail saveUserDetails(UserDetail userDetail) {

        return userDetailsRepository.save(userDetail);
    }

    public List<UserDetail> getUsersDetails() {

        return userDetailsRepository.findAll();
    }

    public String deleteUserDetails(int id) {
        Optional<UserDetail> userDetailsData = this.userDetailsRepository.findById(id);
        //System.out.println(userDetailsData.isPresent());
        if (userDetailsData.isPresent()){
            this.userDetailsRepository.deleteById(id);
            return "deleted successfully";
        }else {
            throw new ResourceNotFoundException("user detail not found");
        }
    }

    public UserDetail getUserDetailsById(int id) {
        Optional<UserDetail> userDetailsData = this.userDetailsRepository.findById(id);
        return userDetailsData.orElseThrow(() -> new ResourceNotFoundException("user details not found"));
    }

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
