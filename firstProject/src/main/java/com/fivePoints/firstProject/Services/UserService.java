package com.fivePoints.firstProject.Services;

import com.fivePoints.firstProject.Models.User;
import com.fivePoints.firstProject.Repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User saveUser(User user){

        return repository.save(user);
    }

    public List<User> getUsers(){

        return repository.findAll();
    }

    public String deleteUser(int id){
        repository.deleteById(id);
        return "deleted successfully";
    }

    public User getUserById(int id){
        return repository.findById(id).get();
    }

    public User updateUser(User user, int id){
        User currentUser = this.repository.findById(id).get();
//        currentUser.setFirstName(user.getFirstName());
//        currentUser.setLastName(user.getLastName());
//        currentUser.setEmail(user.getEmail());
//        currentUser.setPassword(user.getPassword());
        return repository.save(currentUser);
    }
}
