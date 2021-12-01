package icebreaker.project.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icebreaker.project.entity.TeamCodes;
import icebreaker.project.repository.TeamCodesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class TeamCodesService {

	@Autowired
	TeamCodesRepository teamCodesRepository;
	
	public String getMemberCode(String leaderCode) {
		TeamCodes teamCodes = teamCodesRepository.findByleaderCode(leaderCode);
		if (teamCodes != null) {
			return teamCodes.getMemberCode();
		} else {
			return null;
		}
	}
	
	public boolean verifyMemberCode(String memberCode) {
		TeamCodes teamCodes = teamCodesRepository.findByMemberCode(memberCode);
		if (teamCodes != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Long getTeamId(String memberCode) {
		TeamCodes teamCodes = teamCodesRepository.findByMemberCode(memberCode);
		return teamCodes.getTeamId();
	}
	
	public CodeSet createTeamCodes(Long teamId) {
		String leaderCode = RandomStringUtils.randomAlphanumeric(10);
		String memberCode = RandomStringUtils.randomAlphanumeric(10);
		TeamCodes newTeamCodes = new TeamCodes(teamId, leaderCode, memberCode);
		
		teamCodesRepository.saveAndFlush(newTeamCodes);
		return new CodeSet(leaderCode, memberCode);
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CodeSet {
		public String leaderCode;
		public String memberCode;
	}
	
}