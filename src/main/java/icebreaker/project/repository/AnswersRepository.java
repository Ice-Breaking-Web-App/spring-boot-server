package icebreaker.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import icebreaker.project.entity.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
	
	public List<Answers> findByTeamIdAndQNumber(Long teamId, int qNumber);
	public List<Answers> findByTeamIdAndMemberName(Long teamId, String memberName);
	public Answers findByTeamIdAndQNumberAndMemberName(Long teamId, int qNumber, String memberName);
	
}