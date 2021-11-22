package icebreaker.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icebreaker.project.entity.TeamCodes;
import icebreaker.project.repository.TeamCodesRepository;

@Service
public class TeamCodesService {

	@Autowired
	TeamCodesRepository teamCodesRepository;
	
	public String createTeam(TeamCodes teamInfo) { // service method -> ������ ������ ó�� �� DB ���� �κ�

		String memberCode = teamInfo.getMemberCode(); // getter
		teamInfo.setLeaderCode("abcde"); // setter
		
		teamCodesRepository.save(teamInfo); // DB ���� -> TeamCodes class�� entity�� �ش� repository�� �����ؾ���
		
		return "Team created";
	}
}