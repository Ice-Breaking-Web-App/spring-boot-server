package icebreaker.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import icebreaker.project.entity.TeamCodes;

public interface TeamCodesRepository extends JpaRepository<TeamCodes, Long> {
	
	public TeamCodes findByleaderCode(String leaderCode); // entity에서 @Id 로 지정한 값 이외에 접근할 때
	public TeamCodes findByMemberCode(String memberCode);
	
}