package com.maskedsyntax.queriously.config;

import com.maskedsyntax.queriously.dto.QuestionRequestDTO;
import com.maskedsyntax.queriously.entity.Question;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(QuestionRequestDTO.class, Question.class)
                .addMappings(mapper -> {
                    mapper.map(QuestionRequestDTO::getUserId, Question::setUserId);
                    mapper.skip(Question::setId);  // Skip mapping for the `id` field
                });
        return modelMapper;

    }
}
