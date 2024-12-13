package com.maskedsyntax.blog.service.impl;

import com.maskedsyntax.blog.entity.Post;
import com.maskedsyntax.blog.exception.ResourceNotFoundException;
import com.maskedsyntax.blog.payload.PageResponse;
import com.maskedsyntax.blog.payload.PostDTO;
import com.maskedsyntax.blog.repository.PostRepository;
import com.maskedsyntax.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = getPost(postDTO);

        return getPostDTO(postRepository.save(post));
    }

    @Override
    public PageResponse getAllPosts(
            int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = Sort.by(sortField, sortDirection);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> posts = postPage.getContent();
        List<PostDTO> content = posts.stream().map(PostServiceImpl::getPostDTO)
                                     .collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(content);
        pageResponse.setPageNo(pageNo);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotalElements(postPage.getSize());
        pageResponse.setTotalPages(postPage.getTotalPages());
        pageResponse.setLastPage(postPage.isLast());

        return pageResponse;
    }

    @Override
    public PostDTO getPostById(Long id) {
        return postRepository.findById(id).map(PostServiceImpl::getPostDTO)
                             .orElseThrow(
                                     () -> new ResourceNotFoundException(
                                             "post",
                                             "id",
                                             id.toString()
                                     ));
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "post", "id",
                        id.toString()
                ));

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());
        post.setUpdatedAt(new Date());

        return getPostDTO(postRepository.save(post));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
