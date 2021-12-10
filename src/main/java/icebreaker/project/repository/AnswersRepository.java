package icebreaker.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import icebreaker.project.entity.Answers;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
	
	public List<Answers> findByTeamIdAndQuestionNumber(Long teamId, int questionNumber);
	public List<Answers> findByTeamIdAndMemberName(Long teamId, String memberName);
	public Answers findByTeamIdAndQuestionNumberAndMemberName(Long teamId, int questionNumber, String memberName);
	
}