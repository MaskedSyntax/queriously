package com.maskedsyntax.blog.service;

import com.maskedsyntax.blog.payload.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    List<PostDTO> getAllPosts();
}
