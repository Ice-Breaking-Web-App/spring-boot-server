package icebreaker.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icebreaker.project.entity.TeamCodes;
import icebreaker.project.repository.TeamCodesRepository;

@Service
public class TeamCodesService {

	@Autowired
	TeamCodesRepository teamCodesRepository;
	
	public String createTeam(TeamCodes teamInfo) { // service method -> 실질적 데이터 처리 및 DB 접근 부분

		String memberCode = teamInfo.getMemberCode(); // getter
		teamInfo.setLeaderCode("abcde"); // setter
		
		teamCodesRepository.save(teamInfo); // DB 접근 -> TeamCodes class의 entity를 해당 repository에 저장해야함
		
		return "Team created";
	}
}