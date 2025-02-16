package com.maskedsyntax.queriously.service.impl;

import com.maskedsyntax.queriously.dto.QuestionRequestDTO;
import com.maskedsyntax.queriously.dto.QuestionResponseDTO;
import com.maskedsyntax.queriously.entity.Question;
import com.maskedsyntax.queriously.entity.User;
import com.maskedsyntax.queriously.repository.QuestionRepository;
import com.maskedsyntax.queriously.repository.UserRepository;
import com.maskedsyntax.queriously.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link QuestionService} interface for managing questions.
 * <p>
 * This service provides functionality to create, retrieve, update, and delete questions.
 * It interacts with the database via the {@code QuestionRepository} and converts between
 * entity and DTO representations using a {@code ModelMapper}.
 * </p>
 * <p>
 * Method-level security is enforced using {@code @PreAuthorize} annotations to restrict access
 * based on user roles.
 * </p>
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new QuestionServiceImpl with the specified repository and model mapper.
     *
     * @param questionRepository the repository for performing CRUD operations on Question entities.
     * @param modelMapper the mapper to convert between entities and DTOs.
     */
    public QuestionServiceImpl(
            QuestionRepository questionRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates and saves a new question.
     * <p>
     * Only users with the 'ADMIN' role are authorized to invoke this method.
     * </p>
     *
     * @param questionRequestDTO the DTO containing details of the question to be created.
     * @return the saved question as a {@code QuestionResponseDTO}.
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public QuestionResponseDTO saveQuestion(
            QuestionRequestDTO questionRequestDTO) {
        // Map the incoming DTO to a Question entity.
        Question question = modelMapper.map(questionRequestDTO, Question.class);
        // Save the Question entity in the database.
        Question returnedQuestion = questionRepository.save(question);
        // Map the persisted entity back to a response DTO.
        return modelMapper.map(returnedQuestion, QuestionResponseDTO.class);
    }

    /**
     * Retrieves a question by its unique identifier.
     * <p>
     * Accessible to users with either 'ADMIN' or 'USER' roles.
     * </p>
     *
     * @param id the unique identifier of the question.
     * @return the question as a {@code QuestionResponseDTO}.
     * @throws EntityNotFoundException if a question with the specified ID is not found.
     */
    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public QuestionResponseDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(question, QuestionResponseDTO.class);
    }

    /**
     * Retrieves all questions from the database.
     * <p>
     * Accessible to users with either 'ADMIN' or 'USER' roles.
     * </p>
     *
     * @return a list of {@code QuestionResponseDTO} objects representing all questions.
     */
    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<QuestionResponseDTO> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(question -> modelMapper.map(question, QuestionResponseDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Deletes a question by its unique identifier.
     * <p>
     * Only users with the 'ADMIN' role are authorized to perform this operation.
     * </p>
     *
     * @param id the unique identifier of the question to be deleted.
     * @throws EntityNotFoundException if a question with the specified ID does not exist.
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteQuestion(Long id) {
        // Verify that a question with the given ID exists before attempting deletion.
        if (!questionRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Question not found with id: " + id);
        }
        questionRepository.deleteById(id);
    }

    /**
     * Updates an existing question with new details.
     * <p>
     * Only users with the 'ADMIN' role are authorized to update questions.
     * </p>
     *
     * @param id the unique identifier of the question to be updated.
     * @param questionRequestDTO the DTO containing updated question details.
     * @return the updated question as a {@code QuestionResponseDTO}.
     * @throws EntityNotFoundException if a question with the specified ID is not found.
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public QuestionResponseDTO updateQuestion(
            Long id, QuestionRequestDTO questionRequestDTO) {
        // Retrieve the existing question; throw an exception if not found.
        Question question = questionRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        // Update the question fields with values from the request DTO.
        question.setUser(fetchUsers(questionRequestDTO.getUserId()));
        question.setContent(questionRequestDTO.getContent());
        question.setImageUrl(questionRequestDTO.getImageUrl());
        question.setPublished(questionRequestDTO.getPublished());

        // Save the updated question entity.
        Question updatedQuestion = questionRepository.save(question);
        
        // Map the updated entity to a response DTO.
        return modelMapper.map(updatedQuestion, QuestionResponseDTO.class);
    }

    private User fetchUsers(Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
