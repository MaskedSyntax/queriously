package com.maskedsyntax.blog.service;

import com.maskedsyntax.blog.payload.PostDTO;

import java.util.List;

public interface PostService {
    // Create new Post
    PostDTO createPost(PostDTO postDTO);

    // Get All Posts
    List<PostDTO> getAllPosts();

    // Get Post by Id
    PostDTO getPostById(Long id);

    // Update a Post
    PostDTO updatePost(Long id, PostDTO postDTO);

}
