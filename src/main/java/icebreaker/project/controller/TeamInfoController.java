package icebreaker.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	TeamInfoService teamInfoService;
	@Autowired
	TeamCodesService teamCodesService;
	@Autowired
	TeamMembersService teamMembersService;
	@Autowired
	QuestionsService questionsService;
	
	@PutMapping("/create")
	public CodeSet createTeam(@RequestBody InfoSet teamInfo) { // RequestBody, RequestParam
		Long teamId = teamInfoService.createTeam(teamInfo);
		CodeSet codeSet = teamCodesService.createTeamCodes(teamId);
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
	
	@GetMapping("/name")
	public String getTeamName(@RequestParam String memberCode) {
		return teamInfoService.getTeamName(memberCode);
	}
	
	@GetMapping("/pay")
	public boolean getIsPaid(@RequestParam String memberCode) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			return teamInfoService.getIsPaid(memberCode);
		} else {
			return false;
		}
	}
	
	@PatchMapping("/pay")
	public void updateIsPaid(@RequestParam String memberCode, boolean isPaid) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			teamInfoService.updateIsPaid(memberCode, isPaid);
		}
	}

}