package icebreaker.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import icebreaker.project.entity.TeamMembers;

public interface TeamMembersRepository extends JpaRepository<TeamMembers, Long> {
	
	public List<TeamMembers> findByTeamId(Long teamId);
	public TeamMembers findByTeamIdAndMemberName(Long teamId, String memberName);
	
}