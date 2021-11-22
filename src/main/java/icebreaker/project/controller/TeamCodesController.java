package icebreaker.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import icebreaker.project.entity.TeamCodes;
import icebreaker.project.service.TeamCodesService;

@CrossOrigin
@RestController
@RequestMapping("api/team") // base url
public class TeamCodesController {
	
	@Autowired
	TeamCodesService teamCodesService; // 싱글톤 패턴
	
	@PutMapping("/create") // Post, Patch 등 다른 맵핑이 필요하다면 위에 import에 추가
	public String signUp(@RequestBody TeamCodes teamInfo) { // RequestBody, RequestParam
		return teamCodesService.createTeam(teamInfo); // service 호출 (return이 boolean 타입이면 return 없이)
	}

}