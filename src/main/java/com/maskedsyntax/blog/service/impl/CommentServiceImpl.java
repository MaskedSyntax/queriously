package com.maskedsyntax.blog.service.impl;

import com.maskedsyntax.blog.entity.Comment;
import com.maskedsyntax.blog.entity.Post;
import com.maskedsyntax.blog.exception.ResourceNotFoundException;
import com.maskedsyntax.blog.payload.CommentDTO;
import com.maskedsyntax.blog.repository.CommentRepository;
import com.maskedsyntax.blog.repository.PostRepository;
import com.maskedsyntax.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(
            CommentRepository commentRepository,
            PostRepository postRepository
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    private static CommentDTO getCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setBody(comment.getBody());
        commentDTO.setCreated(comment.getCreated());
        return commentDTO;
    }

    private static Comment getComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        comment.setCreated(new Date());
        return comment;
    }

    private Post findPostById(Long postId) {

        return postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "post", "postId",
                        postId.toString()
                ));
    }

    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        Post post = findPostById(postId);

        Comment comment = getComment(commentDTO);
        comment.setPost(post);

        commentRepository.save(comment);

        return getCommentDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, CommentDTO comment) {
        Post post = findPostById(postId);


        return null;
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {

    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> posts = commentRepository.findByPostId(postId);
        return posts.stream().map(CommentServiceImpl::getCommentDTO).collect(
                Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsByPostIdAndUserId(
            Long postId,
            Long userId
    ) {
        return List.of();
    }

    @Override
    public List<CommentDTO> getCommentsByCommentIdAndPostId(
            Long commentId,
            Long postId
    ) {
        return List.of();
    }
}
