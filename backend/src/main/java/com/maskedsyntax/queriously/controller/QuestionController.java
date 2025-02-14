package com.maskedsyntax.queriously.controller;

import com.maskedsyntax.queriously.dto.QuestionRequestDTO;
import com.maskedsyntax.queriously.dto.QuestionResponseDTO;
import com.maskedsyntax.queriously.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing questions.
 * <p>
 * This controller exposes endpoints for creating, retrieving, updating, and deleting questions.
 * It delegates the business logic to the QuestionService.
 * </p>
 */
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    /**
     * Constructs a new QuestionController with the given QuestionService.
     * 
     * @param questionService
     */
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * Creates a new question.
     * 
     * @param questionRequestDTO the DTO containing details of the question to be created.
     * @return a ResponseEntity containing the created QuestionResponseDTO and HTTP status 200 (OK).
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionResponseDTO> addQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        QuestionResponseDTO questionResponseDTO = questionService.saveQuestion(questionRequestDTO);
        return ResponseEntity.ok(questionResponseDTO);
    }

    /**
     * Retrieves a question by its unique identifier.
     *
     * @param id the unique identifier of the question.
     * @return a ResponseEntity containing the found QuestionResponseDTO and HTTP status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> getQuestionById(@PathVariable Long id) {
        QuestionResponseDTO questionResponseDTO = questionService.getQuestionById(id);
        return new ResponseEntity<>(questionResponseDTO, HttpStatus.OK);
    }

    /**
     * Retrieves all questions.
     *
     * @return a ResponseEntity containing a list of QuestionResponseDTO objects and HTTP status 200 (OK).
     */
    @GetMapping("")
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestions() {
        List<QuestionResponseDTO> questions = questionService.getQuestions();
        return ResponseEntity.ok(questions);
    }

    /**
     * Updates an existing question.
     *
     * @param id the unique identifier of the question to be updated.
     * @param questionRequestDTO the DTO containing updated details of the question.
     * @return a ResponseEntity containing the updated QuestionResponseDTO and HTTP status 200 (OK).
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionResponseDTO> updateQuestion(@PathVariable Long id,
            @RequestBody QuestionRequestDTO questionRequestDTO) {
        QuestionResponseDTO questionResponseDTO = questionService.updateQuestion(id, questionRequestDTO);
        return ResponseEntity.ok(questionResponseDTO);
    }

    /**
     * Deletes a question by its unique identifier.
     *
     * @param id the unique identifier of the question to be deleted.
     * @return a ResponseEntity with HTTP status 204 (No Content) indicating that the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestions(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
