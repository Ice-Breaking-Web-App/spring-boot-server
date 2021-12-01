package icebreaker.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icebreaker.project.entity.TeamInfo;
import icebreaker.project.entity.TeamInfo.TeamColors;
import icebreaker.project.repository.TeamInfoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class TeamInfoService {

	@Autowired
	TeamInfoRepository teamInfoRepository;
	@Autowired
	TeamCodesService teamCodesService;
	
	public Long createTeam(InfoSet teamInfo) { // service method -> 실질적 데이터 처리 및 DB 접근 부분
		TeamInfo infoData = new TeamInfo(teamInfo.teamName, teamInfo.leaderName, teamInfo.teamColor, teamInfo.questions.length, teamInfo.members.length);
		TeamInfo savedData = teamInfoRepository.saveAndFlush(infoData);
		return savedData.getTeamId();
	}
	
	public TeamInfo getTeamInfo(String memberCode) {
		Long teamId = teamCodesService.getTeamId(memberCode);
		return teamInfoRepository.findById(teamId).get();
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	public static class InfoSet {
		public String teamName;
		public String leaderName;
		public TeamColors teamColor;
		public String[] questions;
		public String[] members;
	}
}