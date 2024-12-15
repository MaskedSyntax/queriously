package com.maskedsyntax.blog.service.impl;

import com.maskedsyntax.blog.entity.Comment;
import com.maskedsyntax.blog.entity.Post;
import com.maskedsyntax.blog.exception.BlogAPIException;
import com.maskedsyntax.blog.exception.ResourceNotFoundException;
import com.maskedsyntax.blog.payload.CommentDTO;
import com.maskedsyntax.blog.repository.CommentRepository;
import com.maskedsyntax.blog.repository.PostRepository;
import com.maskedsyntax.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public CommentDTO updateComment(
            Long postId, Long commentId, CommentDTO commentDTO) {
        Post post = findPostById(postId);

        Comment comment = commentRepository.findById(commentId)
                                           .orElseThrow(
                                                   () -> new ResourceNotFoundException(
                                                           "Comment",
                                                           "commentId",
                                                           commentId.toString()
                                                   ));
        if (!comment.getPost().equals(post)) {
            throw new BlogAPIException(
                    HttpStatus.BAD_REQUEST, "Comment cannot be updated");
        }

        comment.setBody(commentDTO.getBody());
        return getCommentDTO(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = findPostById(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Comment", "commentId",
                        commentId.toString()
                ));
        if (!comment.getPost().equals(post)) {
            throw new BlogAPIException(
                    HttpStatus.BAD_REQUEST, "Comment cannot be deleted");
        }

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(CommentServiceImpl::getCommentDTO).collect(
                Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsByPostIdAndEmail(
            Long postId,
            String email
    ) {
        List<Comment> comments = commentRepository.findByPostIdAndEmail(
                postId, email);
        return comments.stream().map(CommentServiceImpl::getCommentDTO).collect(
                Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentsByCommentIdAndPostId(
            Long commentId,
            Long postId
    ) {
        List<Comment> comments = commentRepository.findByPostIdAndId(
                postId, commentId);
        return comments.stream().map(CommentServiceImpl::getCommentDTO).collect(
                Collectors.toList());
    }
}
