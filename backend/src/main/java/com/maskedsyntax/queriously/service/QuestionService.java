package com.maskedsyntax.queriously.service;


import com.maskedsyntax.queriously.dto.QuestionRequestDTO;
import com.maskedsyntax.queriously.dto.QuestionResponseDTO;
import com.maskedsyntax.queriously.entity.Question;

import java.util.List;

public interface QuestionService {
    QuestionResponseDTO saveQuestion(QuestionRequestDTO questionRequestDTO);
    QuestionResponseDTO getQuestionById(Long id);
    List<QuestionResponseDTO> getQuestions();
}
