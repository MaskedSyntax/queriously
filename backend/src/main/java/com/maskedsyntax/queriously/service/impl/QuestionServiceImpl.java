package com.maskedsyntax.queriously.service.impl;

import com.maskedsyntax.queriously.dto.QuestionRequestDTO;
import com.maskedsyntax.queriously.dto.QuestionResponseDTO;
import com.maskedsyntax.queriously.entity.Question;
import com.maskedsyntax.queriously.repository.QuestionRepository;
import com.maskedsyntax.queriously.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public QuestionResponseDTO saveQuestion(QuestionRequestDTO questionRequestDTO) {
        // QuestionRequestDTO -> Question
        Question question = modelMapper.map(questionRequestDTO, Question.class);
        // Save Question
        Question returnedQuestion = questionRepository.save(question);
        // Question -> QuestionResponseDTO
        return modelMapper.map(returnedQuestion, QuestionResponseDTO.class);
    }

    @Override
    public QuestionResponseDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(question, QuestionResponseDTO.class);
    }

    @Override
    public List<QuestionResponseDTO> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(question -> modelMapper.map(question, QuestionResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteQuestion(Long id) {
        // check if question for given id exists
        if (!questionRepository.existsById(id)) {
            throw new EntityNotFoundException("Question not found with id: " + id);
        }
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionResponseDTO updateQuestion(Long id, QuestionRequestDTO questionRequestDTO) {
        // check if question for given id exists
        Question question = questionRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        question.setUserId(questionRequestDTO.getUserId());
        question.setContent(questionRequestDTO.getContent());
        question.setImageUrl(questionRequestDTO.getImageUrl());
        question.setPublished(questionRequestDTO.getPublished());

        Question updatedQuestion = questionRepository.save(question);
        return modelMapper.map(updatedQuestion, QuestionResponseDTO.class);
    }
}
