package com.maskedsyntax.queriously.repository;

import com.maskedsyntax.queriously.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
