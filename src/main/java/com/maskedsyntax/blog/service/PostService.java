package com.maskedsyntax.blog.service;

import com.maskedsyntax.blog.payload.PageResponse;
import com.maskedsyntax.blog.payload.PostDTO;

public interface PostService {
    // Create new Post
    PostDTO createPost(PostDTO postDTO);

    // Get All Posts
    PageResponse getAllPosts(int pageNo, int pageSize);

    // Get Post by Id
    PostDTO getPostById(Long id);

    // Update a Post
    PostDTO updatePost(Long id, PostDTO postDTO);

    // Delete Post
    void deletePost(Long id);

}
