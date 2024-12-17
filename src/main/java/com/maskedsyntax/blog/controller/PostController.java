package com.maskedsyntax.blog.controller;

import com.maskedsyntax.blog.payload.PageResponse;
import com.maskedsyntax.blog.payload.PostDTO;
import com.maskedsyntax.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.maskedsyntax.blog.utils.AppConstants.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(
            @Valid @RequestBody PostDTO postDTO
    ) {
        return new ResponseEntity<>(
                postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse> getAllPosts(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NUMBER) int pageNo,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(required = false, defaultValue = DEFAULT_SORT_BY_FIELD) String sortBy,
            @RequestParam(required = false, defaultValue = DEFAULT_SORT_ORDER) String sortOrder
    ) {
        return ResponseEntity.ok(
                postService.getAllPosts(pageNo, pageSize, sortBy, sortOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(
            @PathVariable("id") Long postId
    ) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(
            @PathVariable("id") Long postId,
            @Valid @RequestBody PostDTO postDTO
    ) {
        return ResponseEntity.ok(postService.updatePost(postId, postDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(
                "Post Entity deleted successfully.", HttpStatus.OK);
    }

}
