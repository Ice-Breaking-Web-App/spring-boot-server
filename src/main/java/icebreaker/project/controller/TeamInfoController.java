package icebreaker.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import icebreaker.project.service.TeamInfoService;
import icebreaker.project.entity.TeamInfo;
import icebreaker.project.service.QuestionsService;
import icebreaker.project.service.TeamCodesService;
import icebreaker.project.service.TeamCodesService.CodeSet;
import icebreaker.project.service.TeamInfoService.InfoSet;
import icebreaker.project.service.TeamMembersService;

@CrossOrigin
@RestController
@RequestMapping("api/team") // base url
public class TeamInfoController {
	
	@Autowired
	TeamInfoService teamInfoService; // 싱글톤 패턴
	@Autowired
	TeamCodesService teamCodesService;
	@Autowired
	TeamMembersService teamMembersService;
	@Autowired
	QuestionsService questionsService;
	
	@PutMapping("/create") // Post, Patch 등 다른 맵핑이 필요하다면 위에 import에 추가
	public CodeSet createTeam(@RequestBody InfoSet teamInfo) { // RequestBody, RequestParam
		Long teamId = teamInfoService.createTeam(teamInfo); // service 호출
		CodeSet codeSet = teamCodesService.createTeamCodes(teamId); // 메소드의 return 타입과 같은 타입의 데이터 return
		questionsService.setAllQuestions(teamInfo.questions, teamId);
		teamMembersService.createMembers(teamInfo.leaderName, teamInfo.members, teamId);
		return codeSet;
	}
	
	@GetMapping("/info")
	public TeamInfo getTeamInfo(@RequestParam String memberCode) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			return teamInfoService.getTeamInfo(memberCode);
		} else {
			return null;
		}
	}

}