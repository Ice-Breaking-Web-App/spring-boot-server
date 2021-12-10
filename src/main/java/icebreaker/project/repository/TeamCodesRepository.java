package icebreaker.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import icebreaker.project.entity.TeamCodes;

public interface TeamCodesRepository extends JpaRepository<TeamCodes, Long> {
	
	public TeamCodes findByleaderCode(String leaderCode);
	public TeamCodes findByMemberCode(String memberCode);
	
}