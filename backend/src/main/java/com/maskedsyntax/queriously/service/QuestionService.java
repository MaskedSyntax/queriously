package com.maskedsyntax.queriously.service;


import com.maskedsyntax.queriously.dto.QuestionRequestDTO;
import com.maskedsyntax.queriously.dto.QuestionResponseDTO;

import java.util.List;

public interface QuestionService {
    QuestionResponseDTO saveQuestion(QuestionRequestDTO questionRequestDTO);

    QuestionResponseDTO getQuestionById(Long id);

    List<QuestionResponseDTO> getQuestions();

    void deleteQuestion(Long id);

    QuestionResponseDTO updateQuestion(Long id, QuestionRequestDTO questionRequestDTO);
}
