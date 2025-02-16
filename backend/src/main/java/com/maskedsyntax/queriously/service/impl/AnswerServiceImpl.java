package com.maskedsyntax.queriously.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.maskedsyntax.queriously.dto.AnswerRequestDTO;
import com.maskedsyntax.queriously.dto.AnswerResponseDTO;
import com.maskedsyntax.queriously.entity.Answer;
import com.maskedsyntax.queriously.entity.Question;
import com.maskedsyntax.queriously.entity.User;
import com.maskedsyntax.queriously.repository.AnswerRepository;
import com.maskedsyntax.queriously.repository.QuestionRepository;
import com.maskedsyntax.queriously.repository.UserRepository;
import com.maskedsyntax.queriously.service.AnswerService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    /**
     * @param answerRepository
     * @param modelMapper
     */
    public AnswerServiceImpl(AnswerRepository answerRepository, UserRepository userRepository,
            QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AnswerResponseDTO saveAnswer(AnswerRequestDTO answerRequestDTO) {
        Answer answer = modelMapper.map(answerRequestDTO, Answer.class);
        Answer returnedAnswer = answerRepository.save(answer);

        return modelMapper.map(returnedAnswer, AnswerResponseDTO.class);
    }

    @Override
    public AnswerResponseDTO updateAnswer(Long id, AnswerRequestDTO answerRequestDTO) {
        // TODO: Only allow the owner of the answer to update answer
        Answer answer = answerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        answer.setUser(fetchUser(answerRequestDTO.getUserId()));
        answer.setQuestion(fetchQuestion(answerRequestDTO.getQuestionId()));
        answer.setContent(answerRequestDTO.getContent());
        answer.setImageURL(answerRequestDTO.getImageURL());
        answer.setPublished(answerRequestDTO.getPublished());

        Answer updatedAnswer = answerRepository.save(answer);

        return modelMapper.map(updatedAnswer, AnswerResponseDTO.class);
    }

    @Override
    public void deleteAnswer(Long id) {
        // TODO: Only allow the owner of the answer to delete answer
        if (!answerRepository.existsById(id)) {
            throw new EntityNotFoundException("Answer not found with id: " + id);
        }

        questionRepository.deleteById(id);
    }

    @Override
    public List<AnswerResponseDTO> getAnswers() {
        List<Answer> answers = answerRepository.findAll();
        return answers.stream()
            .map(answer -> modelMapper.map(answers, AnswerResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public AnswerResponseDTO getAnswerById(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(answer, AnswerResponseDTO.class);
    }

    private User fetchUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    private Question fetchQuestion(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(EntityNotFoundException::new);
    }

}
