package icebreaker.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import icebreaker.project.entity.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
	
}