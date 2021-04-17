package com.fivePoints.firstProject.Services;

import com.fivePoints.firstProject.Exceptions.ResourceNotFoundException;
import com.fivePoints.firstProject.Models.Post;
import com.fivePoints.firstProject.Models.User;
import com.fivePoints.firstProject.Repositries.PostsRepository;
import com.fivePoints.firstProject.Repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UserRepository userRepository;

    public  String addPost(Post post , int userId) throws ResourceNotFoundException {

        Optional<User> userdata = this.userRepository.findById(userId);
        if(userdata.isPresent()){
            User user = userdata.orElseThrow(()-> new ResourceNotFoundException("user not found"));
            post.setUser(user);

            this.postsRepository.save(post);
            return "post ajouté avec succés";
        }
        else {
            throw new  ResourceNotFoundException("user not found");
        }
    }

    public List<Post> getPosts() {

        return postsRepository.findAll();
    }

    public String deletePost(int id) {
        Optional<Post> postData = this.postsRepository.findById(id);
        //System.out.println(postData.isPresent());
        if (postData.isPresent()){
            this.postsRepository.deleteById(id);
            return "deleted successfully";
        }else {
            throw new ResourceNotFoundException("post not found");
        }
    }

    public Post getPostById(int id) {
        Optional<Post> postData = this.postsRepository.findById(id);
        return postData.orElseThrow(() -> new ResourceNotFoundException("post not found"));
    }

    public String updatePost(int id , Post post , int userId) throws ResourceNotFoundException {
        Optional<Post> postData = this.postsRepository.findById(id);
        Optional<User> userData = this.userRepository.findById(userId);
        if (postData.isPresent()) {
            User user = userData.orElseThrow(() -> new ResourceNotFoundException("user not found"));
            Post postfound = postData.orElseThrow(() -> new ResourceNotFoundException("post not found"));

            postfound.setTitle(post.getTitle());
            postfound.setDescription(post.getDescription());
            postfound.setPublished(post.getPublished());

            postfound.setUser(user);
            this.postsRepository.save(postfound);
            return "post Technique modifié";
        } else {
            throw new ResourceNotFoundException("post not found");
        }
    }
}
