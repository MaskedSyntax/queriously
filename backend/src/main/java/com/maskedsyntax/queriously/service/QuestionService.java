package com.maskedsyntax.queriously.service;


import com.maskedsyntax.queriously.dto.QuestionRequestDTO;
import com.maskedsyntax.queriously.dto.QuestionResponseDTO;

import java.util.List;

/**
 * Service interface for managing questions in the system.
 * <p>
 * This interface defines methods for creating, retrieving, updating, and deleting questions.
 * Implementations of this interface handle the business logic associated with question management.
 * </p>
 */
public interface QuestionService {
    
    /**
     * Saves a new question to the system.
     *
     * @param questionRequestDTO Data transfer object containing the details of the question to be saved.
     * @return The saved question as a QuestionResponseDTO.
     */
    QuestionResponseDTO saveQuestion(QuestionRequestDTO questionRequestDTO);

    /**
     * Retrieves a question by its unique identifier.
     *
     * @param id The unique identifier of the question.
     * @return The question as a QuestionResponseDTO.
     */
    QuestionResponseDTO getQuestionById(Long id);


    /**
     * Retrieves all questions in the system.
     *
     * @return A list of QuestionResponseDTO objects representing all questions.
     */
    List<QuestionResponseDTO> getQuestions();

    /**
     * Deletes a question from the system.
     *
     * @param id The unique identifier of the question to be deleted.
     */
    void deleteQuestion(Long id);


    /**
     * Updates an existing question.
     *
     * @param id The unique identifier of the question to be updated.
     * @param questionRequestDTO Data transfer object containing the updated question details.
     * @return The updated question as a QuestionResponseDTO.
     */
    QuestionResponseDTO updateQuestion(Long id, QuestionRequestDTO questionRequestDTO);
}
