package com.maskedsyntax.blog.service.impl;

import com.maskedsyntax.blog.entity.Post;
import com.maskedsyntax.blog.payload.PostDTO;
import com.maskedsyntax.blog.repository.PostRepository;
import com.maskedsyntax.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = getPost(postDTO);

        return getPostDTO(postRepository.save(post));
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostServiceImpl::getPostDTO).collect(Collectors.toList());
    }

    private static PostDTO getPostDTO(Post resposePost) {
        PostDTO resposePostDTO = new PostDTO();
        resposePostDTO.setId(resposePost.getId());
        resposePostDTO.setTitle(resposePost.getTitle());
        resposePostDTO.setDescription(resposePost.getDescription());
        resposePostDTO.setContent(resposePost.getContent());
        resposePostDTO.setAuthor(resposePost.getAuthor());
        resposePostDTO.setCreatedAt(resposePost.getCreatedAt());
        resposePostDTO.setUpdatedAt(resposePost.getUpdatedAt());
        return resposePostDTO;
    }

    private static Post getPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());
        return post;
    }
}
