package com.maskedsyntax.queriously.config;

import com.maskedsyntax.queriously.dto.QuestionRequestDTO;
import com.maskedsyntax.queriously.entity.Question;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the ModelMapper bean.
 * <p>
 * This configuration customizes the default behavior of ModelMapper by defining
 * specific mappings between DTOs and entities. In particular, it defines a
 * mapping from {@link QuestionRequestDTO} to {@link Question}, explicitly mapping the
 * userId field and skipping the id field to allow for auto-generation.
 * </p>
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Configures and returns a ModelMapper bean with custom mappings.
     *
     * @return the configured ModelMapper instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Define custom mapping from QuestionRequestDTO to Question
        modelMapper.typeMap(QuestionRequestDTO.class, Question.class)
                .addMappings(mapper -> {
                    mapper.map(QuestionRequestDTO::getUserId, Question::setUserId);
                    mapper.skip(Question::setId); // Skip mapping for the `id` field
                });
        return modelMapper;

    }
}
