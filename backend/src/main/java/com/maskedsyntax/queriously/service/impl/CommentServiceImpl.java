package com.maskedsyntax.queriously.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.maskedsyntax.queriously.dto.CommentRequestDTO;
import com.maskedsyntax.queriously.dto.CommentResponseDTO;
import com.maskedsyntax.queriously.entity.Comment;
import com.maskedsyntax.queriously.entity.User;
import com.maskedsyntax.queriously.exception.UnauthorizedException;
import com.maskedsyntax.queriously.repository.CommentRepository;
import com.maskedsyntax.queriously.repository.UserRepository;
import com.maskedsyntax.queriously.service.CommentService;

import jakarta.persistence.EntityNotFoundException;

public class CommentServiceImpl implements CommentService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    /**
     * @param modelMapper
     * @param userRepository
     * @param commentRepository
     */
    public CommentServiceImpl(ModelMapper modelMapper, UserRepository userRepository,
            CommentRepository commentRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentResponseDTO saveComment(CommentRequestDTO commentRequestDTO) {
        Comment comment = modelMapper.map(commentRequestDTO, Comment.class);
        Comment returnedComment = commentRepository.save(comment);

        return modelMapper.map(returnedComment, CommentResponseDTO.class);
    }

    @Override
    public CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO) {

        // Fetch the comment
        Comment comment = commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        // Get the authenticated user from security context
        User authenticatedUser = getAuthenticatedUser();

        // Ensure only the owner can update
        if (!comment.getUser().getId().equals(authenticatedUser.getId())) {
            throw new UnauthorizedException("User is not authorized to update this comment");
        }

        comment.setContent(commentRequestDTO.getContent());

        Comment updatedcomment = commentRepository.save(comment);

        return modelMapper.map(updatedcomment, CommentResponseDTO.class);
    }

    @Override
    public void deleteComment(Long id) {

        // Fetch the comment or throw an exception if not found
        Comment comment = commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        // Get the authenticated user from security context
        User authenticatedUser = getAuthenticatedUser();

        // Ensure only the owner can delete the comment
        if (!comment.getUser().getId().equals(authenticatedUser.getId())) {
            throw new UnauthorizedException("User is not authorized to delete this comment");
        }

        // Delete the comment
        commentRepository.delete(comment);
    }

    @Override
    public List<CommentResponseDTO> getComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(comment -> modelMapper.map(comments, CommentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponseDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(comment, CommentResponseDTO.class);
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User is not authentication");
        }

        String username = authentication.getName();
        return userRepository.findByUsernameOrEmail(username, username).orElseThrow(EntityNotFoundException::new);
    }
}
