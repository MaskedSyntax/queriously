package com.maskedsyntax.queriously.service;

import java.util.List;

import com.maskedsyntax.queriously.dto.AnswerRequestDTO;
import com.maskedsyntax.queriously.dto.AnswerResponseDTO;

public interface AnswerService {
    // save answer
    AnswerResponseDTO saveAnswer(AnswerRequestDTO answerRequestDTO);
    
    // update answer 
    AnswerResponseDTO updateAnswer(Long id, AnswerRequestDTO answerRequestDTO);
    
    // delete answer
    void deleteAnswer(Long id);

    // list all answers
    List<AnswerResponseDTO> getAnswers();

    // get particular answer -- not exactly needed I guess
    AnswerResponseDTO getAnswerById(Long id);
}
