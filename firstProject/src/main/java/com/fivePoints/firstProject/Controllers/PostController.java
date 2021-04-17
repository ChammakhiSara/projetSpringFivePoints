package com.fivePoints.firstProject.Controllers;

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
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/")
    public ResponseEntity<Post> savePost(@RequestBody Post post)
    {
        Post savedPost =  this.postService.savePost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts()
    {
        List<Post> listPosts = this.postService.getPosts();
        return new ResponseEntity<>(listPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPostByID(@PathVariable("id") int id)
    {
        Post post = this.postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Post> update(@RequestBody Post post, @PathVariable("id") int id){
        Post updatedPost = this.postService.updatePost(post, id);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<ResponseMessage> deletePost (@PathVariable("id") int id){
        String message = this.postService.deletePost(id);
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
    }

}
