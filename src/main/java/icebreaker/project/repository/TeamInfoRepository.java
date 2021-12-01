package icebreaker.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import icebreaker.project.entity.TeamInfo;

public interface TeamInfoRepository extends JpaRepository<TeamInfo, Long> {
	
}