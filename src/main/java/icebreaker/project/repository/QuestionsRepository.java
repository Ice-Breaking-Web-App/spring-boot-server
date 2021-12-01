package icebreaker.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import icebreaker.project.entity.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
	
	public List<Questions> findByTeamId(Long teamId);
	
}