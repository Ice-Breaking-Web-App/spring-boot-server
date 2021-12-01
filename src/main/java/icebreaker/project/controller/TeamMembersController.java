package icebreaker.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import icebreaker.project.entity.TeamMembers;
import icebreaker.project.service.TeamCodesService;
import icebreaker.project.service.TeamMembersService;
import icebreaker.project.service.TeamMembersService.ScoreSet;
import icebreaker.project.service.TeamMembersService.StatusSet;

@CrossOrigin
@RestController
@RequestMapping("api/member")
public class TeamMembersController {
	
	@Autowired
	TeamMembersService teamMembersService;
	@Autowired
	TeamCodesService teamCodesService;
	
	@GetMapping("/status")
	public StatusSet getTeamStatus(@RequestParam String memberCode) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			return teamMembersService.getTeamStatus(memberCode);
		} else {
			return null;
		}
	}
	
	@PatchMapping("/status")
	public void updateMemberStatus(@RequestParam String memberCode, String memberName, String status) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			teamMembersService.updateMemberStatus(memberCode, memberName, status);
		}
	}
	
	@GetMapping("/info")
	public List<TeamMembers> getMembersInfo(@RequestParam String memberCode) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			return teamMembersService.getMembersInfo(memberCode);
		} else {
			return null;
		}
	}
	
	@PatchMapping("/score/member")
	public void setMemberScore(@RequestParam String memberCode, String memberName, int score) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			teamMembersService.setMemberScore(memberCode, memberName, score);
		}
	}
	
	@GetMapping("/score/all")
	public List<ScoreSet> getAllScores(@RequestParam String memberCode) {
		boolean isValid = teamCodesService.verifyMemberCode(memberCode);
		
		if (isValid) {
			return teamMembersService.getAllScores(memberCode);
		} else {
			return null;
		}
	}

}