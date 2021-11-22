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
	TeamCodesService teamCodesService; // �̱��� ����
	
	@PutMapping("/create") // Post, Patch �� �ٸ� ������ �ʿ��ϴٸ� ���� import�� �߰�
	public String signUp(@RequestBody TeamCodes teamInfo) { // RequestBody, RequestParam
		return teamCodesService.createTeam(teamInfo); // service ȣ�� (return�� boolean Ÿ���̸� return ����)
	}

}