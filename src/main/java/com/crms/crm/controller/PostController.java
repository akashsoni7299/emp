package com.crms.crm.controller;

import com.crms.crm.entity.Comment;
import com.crms.crm.entity.Post;
import com.crms.crm.repository.CommentRepository;
import com.crms.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public PostController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping
    public String createPost(@RequestBody Post post){
        postRepository.save(post);
        return "saved";
    }

    @DeleteMapping
    public void deletePost(){
        postRepository.deleteById(52L);
    }

}
