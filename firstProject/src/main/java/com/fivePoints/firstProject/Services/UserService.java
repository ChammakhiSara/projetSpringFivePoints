package com.fivePoints.firstProject.Services;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.fivePoints.firstProject.Exceptions.ResourceNotFoundException;
import com.fivePoints.firstProject.Models.Post;
import com.fivePoints.firstProject.Models.Role;
import com.fivePoints.firstProject.Models.User;
import com.fivePoints.firstProject.Models.UserDetail;
import com.fivePoints.firstProject.Repositries.PostsRepository;
import com.fivePoints.firstProject.Repositries.RoleRepository;
import com.fivePoints.firstProject.Repositries.UserDetailsRepository;
import com.fivePoints.firstProject.Repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    PostsRepository postRepository;

    //add a new user to the dataBase
    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    //get all the list of users added to the dataBase
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //delete one user by his id
    public String deleteUserById(int id) {
        Optional<User> userData = this.userRepository.findById(id);
        //System.out.println(userData.isPresent());
        if (userData.isPresent()){
            this.userRepository.deleteById(id);
            return "deleted successfully";
        }else {
            throw new ResourceNotFoundException("user not found");
        }
    }

    //get or find one user by his id
    public User findUserById(int id) {
        Optional<User> userData = this.userRepository.findById(id);
        return userData.orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    //update the user by his id and the object user wanted to update
    public User updateUserById(User user, int id) {
        Optional<User> userData = this.userRepository.findById(id);
        if (userData.isPresent()) {
            User currentUser = userData.orElse(null);
            //set the changes
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(user.getPassword());
            //save the user updated
            User updatedUser = this.userRepository.save(currentUser);
            return updatedUser;
        } else {
            throw new ResourceNotFoundException("user not found");
        }
    }

    // Affecter Role to user
    public String affectUserToRole(int user_id, int role_id) {
        Optional<User> userData = this.userRepository.findById(user_id);
        if (userData.isPresent()) {
            User existingUser = userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));
            Optional<Role> roleData = this.roleRepository.findById(role_id);
            if (roleData.isPresent()) {
                Role existingRole = roleData.orElseThrow(() -> new ResourceNotFoundException("Role not found"));
                Set<Role> role = existingUser.getRole();
                role.add(existingRole);
                existingUser.setRole(role);
                this.userRepository.save(existingUser);
            }
        }
        return "User affected to role successfully!";
    }

    //custom query added to the userRepository (the first way)
    public User getUserByEmailAndFirstName (String email, String firstName)  {
        Optional<User> userData = this.userRepository.findUserByEmailAndFirstName(email, firstName);
        return  userData.orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    //custom query added to the userRepository (the second way)
    public User getUserByEmailAndFtName (String email, String firstName)  {
        Optional<User> userData = this.userRepository.findUserByEmailAndFirstNameV2(email, firstName);
        return  userData.orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

}
