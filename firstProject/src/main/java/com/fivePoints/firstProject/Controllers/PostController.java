package com.fivePoints.firstProject.Controllers;

import com.fivePoints.firstProject.Exceptions.ResourceNotFoundException;
import com.fivePoints.firstProject.Models.Post;
import com.fivePoints.firstProject.Payload.Responses.ResponseMessage;
import com.fivePoints.firstProject.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    PostService postService;

    //methode POST with URL to add new post
    @PostMapping("/{user_Id}")
    public ResponseEntity<ResponseMessage> savePost(@RequestBody Post post, @PathVariable(value = "user_Id")int user_Id) throws ResourceNotFoundException
    {
        String message =  this.postService.addNewPost(post, user_Id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.CREATED);
    }

    //methode GET with URL to get all the posts from dataBase
    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts()
    {
        List<Post> listPosts = this.postService.getAllPosts();
        return new ResponseEntity<>(listPosts, HttpStatus.OK);
    }

    //methode GET with URL to find one post
    @GetMapping("/{id}")
    public ResponseEntity<?> findPostByID(@PathVariable("id") int id)
    {
        Post post = this.postService.findPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //methode PUT with URL to update existing post
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<ResponseMessage> updatedPost(@RequestBody Post post, @PathVariable("id") int id){
        String message = this.postService.updatePostById(post, id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

    //methode DELETE with URL to delete one post
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<ResponseMessage> deletePost (@PathVariable("id") int id){
        String message = this.postService.deletePostById(id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

}
