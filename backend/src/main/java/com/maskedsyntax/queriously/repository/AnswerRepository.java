package com.maskedsyntax.queriously.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maskedsyntax.queriously.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
