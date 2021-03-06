package icebreaker.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import icebreaker.project.service.TeamCodesService;

@CrossOrigin
@RestController
@RequestMapping("api/code")
public class TeamCodesController {
	
	@Autowired
	TeamCodesService teamCodesService;
	
	@GetMapping("/member")
	public String getMemberCode(@RequestParam String leaderCode) {
		return teamCodesService.getMemberCode(leaderCode);
	}
	
	@GetMapping("/verification")
	public boolean verifyMemberCode(@RequestParam String memberCode) {
		return teamCodesService.verifyMemberCode(memberCode);
	}

}