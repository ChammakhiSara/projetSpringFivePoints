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

    //add a new post to the dataBase
    public  String addNewPost(Post post , int user_id) throws ResourceNotFoundException {
        Optional<User> userdata = this.userRepository.findById(user_id);
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

    //get all the list of posts added to the dataBase
    public List<Post> getAllPosts() {
        return this.postsRepository.findAll();
    }

    //delete one post by her id
    public String deletePostById(int id) {
        Optional<Post> postData = this.postsRepository.findById(id);
        //System.out.println(postData.isPresent());
        if (postData.isPresent()){
            this.postsRepository.deleteById(id);
            return "deleted successfully";
        }else {
            throw new ResourceNotFoundException("post not found");
        }
    }

    //get or find one post by her id
    public Post findPostById(int id) {
        Optional<Post> postData = this.postsRepository.findById(id);
        return postData.orElseThrow(() -> new ResourceNotFoundException("post not found"));
    }

    //update the post by her id and the object post wanted to update
    public String updatePostById(Post post, int id) throws ResourceNotFoundException {
        Optional<Post> postData = this.postsRepository.findById(id);
        if (postData.isPresent()) {
            Post postfound = postData.orElseThrow(() -> new ResourceNotFoundException("post not found"));

            postfound.setTitle(post.getTitle());
            postfound.setDescription(post.getDescription());
            postfound.setPublished(post.getPublished());

            this.postsRepository.save(postfound);
            return "post modifié";
        } else {
            throw new ResourceNotFoundException("post not found");
        }
    }

}
