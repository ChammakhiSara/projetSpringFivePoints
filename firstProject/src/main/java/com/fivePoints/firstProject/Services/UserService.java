package com.fivePoints.firstProject.Services;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.fivePoints.firstProject.Exceptions.ResourceNotFoundException;
import com.fivePoints.firstProject.Models.User;
import com.fivePoints.firstProject.Repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public String deleteUser(int id) {
        Optional<User> userData = this.userRepository.findById(id);
        System.out.println(userData.isPresent());
        if (userData.isPresent()){
            this.userRepository.deleteById(id);
            return "deleted successfully";
        }else {
            throw new ResourceNotFoundException("user not found");
        }
    }

    public User getUserById(int id) {
        Optional<User> userData = this.userRepository.findById(id);
        return userData.orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    public User updateUser(User user, int id) {

        Optional<User> userData = this.userRepository.findById(id);
        if (userData.isPresent()) {
            User currentUser = userData.orElse(null);
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(user.getPassword());
            User updatedUser = this.userRepository.save(currentUser);
            return updatedUser;
        } else {
            throw new ResourceNotFoundException("user not found");
        }
        }

    public User getUserByEmailAndFirstName (String email, String firstName)  {
        return this.userRepository.findUserByEmailAndFirstName(email, firstName);
    }

    public User getUserByEmailAndFtName (String email, String firstName)  {
        Optional<User> userData = this.userRepository.findUserByEmailAndFirstNameV2(email, firstName);
        return  userData.orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

}
